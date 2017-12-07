package com.wph.information.websocket;

import java.sql.Timestamp;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wph.connect.WebConnect;
import com.wph.connect.service.solve.WebConnectServiceSolve;
import com.wph.entities.Msg;
import com.wph.entities.Terminal;
import com.wph.service.CustomerService;
import com.wph.service.CustomeronlineService;
import com.wph.service.CustomerserviceService;
import com.wph.service.CustomerserviceonlineService;
import com.wph.service.MsgService;
import com.wph.service.MsgtypeService;
import com.wph.service.TerminalService;
import com.wph.service.TerminaltypeService;
import com.wph.util.impl.JsonUtils;
import com.wph.util.impl.SpringUtils;

@Service("webSocketPackingService")
public class WebSocketPackingServiceImpl implements WebSocketPackingService {
	// ********************************************************************************
	// ==========================service对象============================================
	@Resource
	private MsgService msgService;
	@Resource
	private TerminalService terminalService;
	@Resource
	private TerminaltypeService terminaltypeService;

	public void setMsgService(MsgService msgService) {
		this.msgService = msgService;
	}

	public void setTerminalService(TerminalService terminalService) {
		this.terminalService = terminalService;
	}

	public void setTerminaltypeService(TerminaltypeService terminaltypeService) {
		this.terminaltypeService = terminaltypeService;
	}

	// ********************************************************************************
	// ==========================全局保存msg对象=========================================
	private Msg receivemsg;
	private Msg responsemsg;

	// ********************************************************************************
	// ==========================获得Service服务对象======================================
	@Resource
	private CustomerserviceService customerserviceService;
	@Resource
	private MsgtypeService msgtypeService;
	@Resource
	private CustomerService customerService;
	@Resource
	private CustomeronlineService customeronlineService;
	@Resource
	private CustomerserviceonlineService customerserviceonlineService;

	public void setCustomerserviceService(CustomerserviceService customerserviceService) {
		this.customerserviceService = customerserviceService;
	}

	public void setMsgtypeService(MsgtypeService msgtypeService) {
		this.msgtypeService = msgtypeService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setCustomeronlineService(CustomeronlineService customeronlineService) {
		this.customeronlineService = customeronlineService;
	}

	public void setCustomerserviceonlineService(CustomerserviceonlineService customerserviceonlineService) {
		this.customerserviceonlineService = customerserviceonlineService;
	}

	// ********************************************************************************
	// ==========================获得需要的Service========================================
	/**
	 * 第一步:获得Connect层的消息
	 */
	@Override
	public void getMsg(String message, WebConnect webConnect) {
		System.out.println("第二层：msgPacking");
		receivemsg = msgPacking(message);
		System.out.println("第二层：saveMsg");
		// WebConnect webConnect = SpringUtils.getBean("webConnect");
		// this.webConnect = webConnect;
		saveMsg(receivemsg);
		System.out.println("第二层：msgFilter");
		msgFilter(receivemsg);
	}

	/**
	 * 第二步：封装消息 同时要将session 和 使用session的id绑定
	 */
	@Override
	public Msg msgPacking(String message) {
		Msg msg = new Msg();
		System.out.println("第二层:msgPacking:msgMap");
		Map<String, Object> msgMap = JsonUtils.jsonToMap(message);
		// 这里千万小心，坑了我好久，如果服务器传过来的数据不带双引号只能用Integer转换，不然就会类型转换报错
		Integer msgtype = null;
		Integer customer = null;
		Integer customerservice = null;
		String content = null;
		System.out.println("第二层:msgPacking:get1");
		if (msgMap.get("msgtype_id") != null) {
			msgtype = (Integer) msgMap.get("msgtype_id");
			msg.setMsgtype(msgtypeService.get(msgtype));
		}
		System.out.println("第二层:msgPacking:get2");
		if (msgMap.get("customer_id") != null) {
			customer = (Integer) msgMap.get("customer_id");
			msg.setCustomer(customerService.get(customer));
		}
		System.out.println("第二层:msgPacking:get3");
		if (msgMap.get("customerservice_id") != null) {
			customerservice = (Integer) msgMap.get("customerservice_id");

			msg.setCustomerservice(customerserviceService.get(customerservice));
		}
		System.out.println("第二层:msgPacking:get4");
		if (msgMap.get("content") != null) {
			System.out.println(msgMap.get("content").toString());
			content = (String) msgMap.get("content");
			msg.setContent(content);
		}
		if (content != null) {

		}
		System.out.println("第二层:msgPacking:setsendtime");
		msg.setSendtime(new Timestamp(System.currentTimeMillis()));
		// 这个方法之后用分词实现
		System.out.println("第二层:msgPacking:setword");
		msg.setSensitiveword(null);
		return msg;
	}

	/**
	 * 第三步：保存消息
	 */
	@Override
	public void saveMsg(Msg msg) {
		msgService.save(msg);
	}

	/**
	 * 第四步:初步消息过滤
	 */
	@Override
	public void msgFilter(Msg msg) {
		// 初步的消息分类处理
		WebConnect webConnect = (WebConnect) SpringUtils.getBean("webConnect");
		System.out.println(msg.getMsgtype().getId());
		Integer msgtype = msg.getMsgtype().getId();
		if ((msgtype >= 1000) && (msgtype < 2000)) {
			// 表明发送方为客服
			if (msgtype == 1300) {
				// 如果为1300,说明是客服初始化信息
				System.out.println("第二层:客服绑定session");
				Integer id = msg.getCustomerservice().getCsId();
				Terminal terminal = terminalService.saveTerminal(id, 100, "客服");
				System.out.println(terminal);
				customerserviceonlineService.saveCustomerserviceonline(id, terminal);
			}
		} else if ((msgtype >= 2000) && (msgtype < 3000)) {
			// 表明发送方为客户
			if (msgtype == 2300) {
				// 如果为2300,说明是客户初始化信息
				System.out.println("第二层:客户绑定session");
				Integer id = msg.getCustomer().getCtId();
				Terminal terminal = terminalService.saveTerminal(id, 100, "客户");
				customeronlineService.saveCustomeronline(id, terminal);
			}
		} else if ((msgtype >= 3000) && (msgtype < 4000)) {
			// 表明发送方为服务器
		} else if ((msgtype >= 4000) && (msgtype < 5000)) {
			// 表明未识别状态码
		} else {
			System.out.println("msgtype Error");
		}
		// 初步的消息过滤
		switch (msgtype) {
		// 1.客服消息结束请求
		case 1302:
			responsemsg = new Msg();
			responsemsg.setMsgtype(msgtypeService.get(3202));
			responsemsg.setSendtime(new Timestamp(System.currentTimeMillis()));
			responsemsg.setCustomer(receivemsg.getCustomer());
			saveMsg(responsemsg);
			webConnect.responseMsg(responsemsg);
			break;
		default:
			/**
			 * 过滤之后 第五步：传递消息
			 */
			WebConnectServiceSolve webConnectServiceSolve = (WebConnectServiceSolve) SpringUtils
					.getBean("webConnectServiceSolve");
			webConnectServiceSolve.msgSubmit(msg, null);
			break;
		}

	}

	// ********************************************************************************
	// ==========================获得上层msg对象并解析=====================================
	@Override
	public void sendMsg(Msg msg, Terminal terminal) {
		System.out.println("第二层：sendMsg");
		Integer statusid = terminal.getStatusid();
		WebConnect webConnect = (WebConnect) SpringUtils.getBean("webConnect");
		webConnect.send(msg, statusid);
	}
}
