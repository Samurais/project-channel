package com.wph.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.lionsoul.jcseg.tokenizer.core.JcsegException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Customer;
import com.wph.entities.Msg;
import com.wph.entities.Msgtype;
import com.wph.entities.Robotknowledgebase;
import com.wph.service.CustomerService;
import com.wph.service.MsgService;
import com.wph.service.MsgtypeService;
import com.wph.service.RobotknowledgebaseService;
import com.wph.util.Jcseg;

import net.sf.json.JSONObject;

@Controller("robotAction")
@Scope("prototype")
public class RobotAction {
	// ********************************************************************************
	// ===================================分词工具类=====================================
	@Resource
	private Jcseg jcseg;

	public Jcseg getJcseg() {
		return jcseg;
	}

	// ********************************************************************************
	// ===================================分词工具类=====================================
	@Resource
	private RobotknowledgebaseService robotknowledgebaseService;

	public void setRobotknowledgebaseService(RobotknowledgebaseService robotknowledgebaseService) {
		this.robotknowledgebaseService = robotknowledgebaseService;
	}

	// ********************************************************************************
	// ==================================Service对象====================================
	@Resource
	private MsgService msgService;
	@Resource
	private MsgtypeService msgtypeService;
	@Resource
	private CustomerService customerService;

	public void setMsgService(MsgService msgService) {
		this.msgService = msgService;
	}

	public void setMsgtypeService(MsgtypeService msgtypeService) {
		this.msgtypeService = msgtypeService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// ********************************************************************************
	// ==================================Json对象=======================================
	private Map<String, Object> jsonMap;

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	// ********************************************************************************
	// ==================================参数对象========================================
	private Integer customer_id;
	private String content;
	private Integer msgtype_id;

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setMsgtype_id(Integer msgtype_id) {
		this.msgtype_id = msgtype_id;
	}

	// ********************************************************************************
	// ==================================Action========================================
	/**
	 * 步骤： 1.过滤字符串，如果有'请求人工'字样，则执行请求人工服务 2.调用API发送消息
	 * 3.检查返回命令码，如果出错，则查询机器人数据库，如果查询没有内容，则记录要求 4.将消息返回,并返回状态码
	 * 
	 * @return
	 * @throws IOException
	 */
	public String toRobot() throws IOException {
		// *********************************************************
		// =====================保存请求信息===========================
		Msg msg = new Msg();
		if (customer_id != null) {
			Customer customer = customerService.get(customer_id);
			msg.setCustomer(customer);
		}
		if (msgtype_id != null) {
			Msgtype msgtype = msgtypeService.get(msgtype_id);
			msg.setMsgtype(msgtype);
		}
		msg.setContent(content);
		msg.setSendtime(new Timestamp(System.currentTimeMillis()));
		msgService.save(msg);
		System.out.println(content);
		// *********************************************************
		// =====================解析字符串,是否是请求人工================
		if (content.indexOf("请求人工") != -1) {
			// 跳转到CustomerserviceAction进行人工请求
			System.out.println("RobotActon:dispatcher");
			return "dispatcher_waitonlineAction";
		} else if (content.indexOf("@") != -1) {
			// 这里进行知识库的检索，如果检索不到便记录问题，然后再发问题给机器人
			System.out.println("RobotActon:checkknow");
			List<String> keywords = null;
			try {
				String str = content.substring(content.indexOf("@") + 1, content.length());
				keywords = jcseg.createKeywordsExtractorCOMPLEX().getKeywordsFromString(str);
			} catch (JcsegException e) {
				e.printStackTrace();
			}
			Robotknowledgebase base = null;
			for (int i = 0; i < keywords.size(); i++) {
				String firstkeywords = keywords.get(i);
				base = robotknowledgebaseService.searchByKeyWord(firstkeywords);
				if (base != null) {
					break;
				}
			}
			jsonMap = new HashMap<String, Object>();
			Msg responsemsg = new Msg();
			Customer cust = null;
			Msgtype msgtype = null;
			String response = null;
			if (customer_id != null) {
				cust = customerService.get(customer_id);
				responsemsg.setCustomer(cust);
				jsonMap.put("customer_id", customer_id);
				if (base != null) {
					response = base.getContent();
					response = "{'code':100000,'text':'" + response + "'}";
				} else {
					response = robotResponse(content, customer_id);
				}
			} else {
				if (base != null) {
					response = base.getContent();
					response = "{'code':100000,'text':'" + response + "'}";
				} else {
					response = robotResponse(content);
				}
			}
			if (msgtype_id != null) {
				msgtype = msgtypeService.get(3203);
				responsemsg.setMsgtype(msgtype);
				jsonMap.put("msgtype_id", 3203);
			}
			Timestamp time = new Timestamp(System.currentTimeMillis());
			responsemsg.setSendtime(time);
			responsemsg.setContent(response);
			jsonMap.put("content", JSONObject.fromObject(response));
			msgService.save(responsemsg);
			return "jsonMap";
		} else {
			// 如果不包含人工，则发送给机器人
			// 返回状态码3203
			System.out.println("RobotActon:sendtorobot");
			jsonMap = new HashMap<String, Object>();
			Msg responsemsg = new Msg();
			Customer cust = null;
			Msgtype msgtype = null;
			String response = null;
			if (customer_id != null) {
				cust = customerService.get(customer_id);
				responsemsg.setCustomer(cust);
				response = robotResponse(content, customer_id);
				jsonMap.put("customer_id", customer_id);
			} else {
				response = robotResponse(content);
			}
			if (msgtype_id != null) {
				msgtype = msgtypeService.get(3203);
				responsemsg.setMsgtype(msgtype);
				jsonMap.put("msgtype_id", 3203);
			}
			Timestamp time = new Timestamp(System.currentTimeMillis());
			responsemsg.setSendtime(time);
			responsemsg.setContent(response);
			jsonMap.put("content", JSONObject.fromObject(response));
			msgService.save(responsemsg);
			return "jsonMap";
		}
	}

	// ********************************************************************************
	// ==================================图灵API方法=====================================
	/**
	 * @param question
	 *            客户发送内容
	 * @param userid
	 *            客户id
	 * @return 图灵返回的Map类型数据
	 * @throws IOException
	 */
	public String robotResponse(String question, Integer userid) throws IOException {
		String APIKEY = "eccb5d806b254c888485d89cc08b13c9";
		String INFO = URLEncoder.encode(question, "utf-8");
		String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO + "&userid=" + userid;
		URL getUrl = new URL(getURL);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.connect();

		// 取得输入流，并使用Reader读取
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		// 断开连接
		connection.disconnect();
		System.out.println(sb);
		return sb.toString();
	}

	public String robotResponse(String question) throws IOException {
		String APIKEY = "eccb5d806b254c888485d89cc08b13c9";
		String INFO = URLEncoder.encode(question, "utf-8");
		String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
		URL getUrl = new URL(getURL);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.connect();

		// 取得输入流，并使用Reader读取
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		// 断开连接
		connection.disconnect();
		System.out.println(sb);
		return sb.toString();
	}
}
