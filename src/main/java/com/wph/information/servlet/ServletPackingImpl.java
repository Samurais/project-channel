package com.wph.information.servlet;

import java.sql.Timestamp;
import java.util.Map;

import javax.annotation.Resource;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;

import com.wph.connect.WebConnect;
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

import net.sf.json.JSONObject;

@Controller("servletPacking")
public class ServletPackingImpl implements ServletPacking {
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
	// ********************************************************************************
	// ==========================全局保存msg对象=========================================
	private Msg receivemsg;
	private Msg responsemsg;

	@Override
	public void getMsg(String message, WebConnect webConnect) {
		// TODO 自动生成的方法存根

	}

	@Override
	public Msg msgPacking(ReceiveXmlEntity xmlEntity) {
//		Msg msg = new Msg();
//		System.out.println("第二层:msgPacking:msgMap");
//		Map<String, Object> msgMap = JsonUtils.jsonToMap(message);
//		Integer msgtype = null;
//		Integer customer = null;
//		Integer customerservice = null;
//		String content = null;
//		System.out.println("第二层:msgPacking:get1");
//		if (msgMap.get("msgtype_id") != null) {
//			msgtype = (Integer) msgMap.get("msgtype_id");
//			msg.setMsgtype(msgtypeService.get(msgtype));
//		}
//		System.out.println("第二层:msgPacking:get2");
//		if (msgMap.get("customer_id") != null) {
//			customer = (Integer) msgMap.get("customer_id");
//			msg.setCustomer(customerService.get(customer));
//		}
//		System.out.println("第二层:msgPacking:get3");
//		if (msgMap.get("customerservice_id") != null) {
//			customerservice = (Integer) msgMap.get("customerservice_id");
//
//			msg.setCustomerservice(customerserviceService.get(customerservice));
//		}
//		System.out.println("第二层:msgPacking:get4");
//		if (msgMap.get("content") != null) {
//			System.out.println(msgMap.get("content").toString());
//			content = (String) msgMap.get("content");
//			msg.setContent(content);
//		}
//		if (content != null) {
//
//		}
//		System.out.println("第二层:msgPacking:setsendtime");
//		msg.setSendtime(new Timestamp(System.currentTimeMillis()));
//		// 这个方法之后用分词实现
//		System.out.println("第二层:msgPacking:setword");
//		msg.setSensitiveword(null);
//		return msg;
		return null;
	}

	@Override
	public void saveMsg(Msg msg) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void msgFilter(Msg msg) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void sendMsg(Msg msg, Terminal terminal) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void submitMsg(ReceiveXmlEntity xmlEntity) {
		System.out.println("第二层：msgPacking");
		receivemsg = msgPacking(xmlEntity);
		System.out.println("第二层：saveMsg");
		saveMsg(receivemsg);
		System.out.println("第二层：msgFilter");
		msgFilter(receivemsg);

	}

}
