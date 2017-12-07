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
	// ===================================�ִʹ�����=====================================
	@Resource
	private Jcseg jcseg;

	public Jcseg getJcseg() {
		return jcseg;
	}

	// ********************************************************************************
	// ===================================�ִʹ�����=====================================
	@Resource
	private RobotknowledgebaseService robotknowledgebaseService;

	public void setRobotknowledgebaseService(RobotknowledgebaseService robotknowledgebaseService) {
		this.robotknowledgebaseService = robotknowledgebaseService;
	}

	// ********************************************************************************
	// ==================================Service����====================================
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
	// ==================================Json����=======================================
	private Map<String, Object> jsonMap;

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	// ********************************************************************************
	// ==================================��������========================================
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
	 * ���裺 1.�����ַ����������'�����˹�'��������ִ�������˹����� 2.����API������Ϣ
	 * 3.��鷵�������룬����������ѯ���������ݿ⣬�����ѯû�����ݣ����¼Ҫ�� 4.����Ϣ����,������״̬��
	 * 
	 * @return
	 * @throws IOException
	 */
	public String toRobot() throws IOException {
		// *********************************************************
		// =====================����������Ϣ===========================
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
		// =====================�����ַ���,�Ƿ��������˹�================
		if (content.indexOf("�����˹�") != -1) {
			// ��ת��CustomerserviceAction�����˹�����
			System.out.println("RobotActon:dispatcher");
			return "dispatcher_waitonlineAction";
		} else if (content.indexOf("@") != -1) {
			// �������֪ʶ��ļ�������������������¼���⣬Ȼ���ٷ������������
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
			// ����������˹������͸�������
			// ����״̬��3203
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
	// ==================================ͼ��API����=====================================
	/**
	 * @param question
	 *            �ͻ���������
	 * @param userid
	 *            �ͻ�id
	 * @return ͼ�鷵�ص�Map��������
	 * @throws IOException
	 */
	public String robotResponse(String question, Integer userid) throws IOException {
		String APIKEY = "eccb5d806b254c888485d89cc08b13c9";
		String INFO = URLEncoder.encode(question, "utf-8");
		String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO + "&userid=" + userid;
		URL getUrl = new URL(getURL);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.connect();

		// ȡ������������ʹ��Reader��ȡ
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		// �Ͽ�����
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

		// ȡ������������ʹ��Reader��ȡ
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		// �Ͽ�����
		connection.disconnect();
		System.out.println(sb);
		return sb.toString();
	}
}
