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
	// ==========================service����============================================
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
	// ==========================ȫ�ֱ���msg����=========================================
	private Msg receivemsg;
	private Msg responsemsg;

	// ********************************************************************************
	// ==========================���Service�������======================================
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
	// ==========================�����Ҫ��Service========================================
	/**
	 * ��һ��:���Connect�����Ϣ
	 */
	@Override
	public void getMsg(String message, WebConnect webConnect) {
		System.out.println("�ڶ��㣺msgPacking");
		receivemsg = msgPacking(message);
		System.out.println("�ڶ��㣺saveMsg");
		// WebConnect webConnect = SpringUtils.getBean("webConnect");
		// this.webConnect = webConnect;
		saveMsg(receivemsg);
		System.out.println("�ڶ��㣺msgFilter");
		msgFilter(receivemsg);
	}

	/**
	 * �ڶ�������װ��Ϣ ͬʱҪ��session �� ʹ��session��id��
	 */
	@Override
	public Msg msgPacking(String message) {
		Msg msg = new Msg();
		System.out.println("�ڶ���:msgPacking:msgMap");
		Map<String, Object> msgMap = JsonUtils.jsonToMap(message);
		// ����ǧ��С�ģ������Һþã���������������������ݲ���˫����ֻ����Integerת������Ȼ�ͻ�����ת������
		Integer msgtype = null;
		Integer customer = null;
		Integer customerservice = null;
		String content = null;
		System.out.println("�ڶ���:msgPacking:get1");
		if (msgMap.get("msgtype_id") != null) {
			msgtype = (Integer) msgMap.get("msgtype_id");
			msg.setMsgtype(msgtypeService.get(msgtype));
		}
		System.out.println("�ڶ���:msgPacking:get2");
		if (msgMap.get("customer_id") != null) {
			customer = (Integer) msgMap.get("customer_id");
			msg.setCustomer(customerService.get(customer));
		}
		System.out.println("�ڶ���:msgPacking:get3");
		if (msgMap.get("customerservice_id") != null) {
			customerservice = (Integer) msgMap.get("customerservice_id");

			msg.setCustomerservice(customerserviceService.get(customerservice));
		}
		System.out.println("�ڶ���:msgPacking:get4");
		if (msgMap.get("content") != null) {
			System.out.println(msgMap.get("content").toString());
			content = (String) msgMap.get("content");
			msg.setContent(content);
		}
		if (content != null) {

		}
		System.out.println("�ڶ���:msgPacking:setsendtime");
		msg.setSendtime(new Timestamp(System.currentTimeMillis()));
		// �������֮���÷ִ�ʵ��
		System.out.println("�ڶ���:msgPacking:setword");
		msg.setSensitiveword(null);
		return msg;
	}

	/**
	 * ��������������Ϣ
	 */
	@Override
	public void saveMsg(Msg msg) {
		msgService.save(msg);
	}

	/**
	 * ���Ĳ�:������Ϣ����
	 */
	@Override
	public void msgFilter(Msg msg) {
		// ��������Ϣ���ദ��
		WebConnect webConnect = (WebConnect) SpringUtils.getBean("webConnect");
		System.out.println(msg.getMsgtype().getId());
		Integer msgtype = msg.getMsgtype().getId();
		if ((msgtype >= 1000) && (msgtype < 2000)) {
			// �������ͷ�Ϊ�ͷ�
			if (msgtype == 1300) {
				// ���Ϊ1300,˵���ǿͷ���ʼ����Ϣ
				System.out.println("�ڶ���:�ͷ���session");
				Integer id = msg.getCustomerservice().getCsId();
				Terminal terminal = terminalService.saveTerminal(id, 100, "�ͷ�");
				System.out.println(terminal);
				customerserviceonlineService.saveCustomerserviceonline(id, terminal);
			}
		} else if ((msgtype >= 2000) && (msgtype < 3000)) {
			// �������ͷ�Ϊ�ͻ�
			if (msgtype == 2300) {
				// ���Ϊ2300,˵���ǿͻ���ʼ����Ϣ
				System.out.println("�ڶ���:�ͻ���session");
				Integer id = msg.getCustomer().getCtId();
				Terminal terminal = terminalService.saveTerminal(id, 100, "�ͻ�");
				customeronlineService.saveCustomeronline(id, terminal);
			}
		} else if ((msgtype >= 3000) && (msgtype < 4000)) {
			// �������ͷ�Ϊ������
		} else if ((msgtype >= 4000) && (msgtype < 5000)) {
			// ����δʶ��״̬��
		} else {
			System.out.println("msgtype Error");
		}
		// ��������Ϣ����
		switch (msgtype) {
		// 1.�ͷ���Ϣ��������
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
			 * ����֮�� ���岽��������Ϣ
			 */
			WebConnectServiceSolve webConnectServiceSolve = (WebConnectServiceSolve) SpringUtils
					.getBean("webConnectServiceSolve");
			webConnectServiceSolve.msgSubmit(msg, null);
			break;
		}

	}

	// ********************************************************************************
	// ==========================����ϲ�msg���󲢽���=====================================
	@Override
	public void sendMsg(Msg msg, Terminal terminal) {
		System.out.println("�ڶ��㣺sendMsg");
		Integer statusid = terminal.getStatusid();
		WebConnect webConnect = (WebConnect) SpringUtils.getBean("webConnect");
		webConnect.send(msg, statusid);
	}
}
