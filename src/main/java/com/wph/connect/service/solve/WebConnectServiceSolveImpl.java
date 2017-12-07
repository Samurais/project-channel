package com.wph.connect.service.solve;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.wph.connect.service.WebConnectService;
import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.entities.Customerserviceonline;
import com.wph.entities.Msg;
import com.wph.entities.Terminal;
import com.wph.service.CustomeronlineService;
import com.wph.service.CustomerserviceonlineService;
import com.wph.service.TerminalService;
import com.wph.service.TerminaltypeService;

@Service("webConnectServiceSolve")
@Scope("prototype")
public class WebConnectServiceSolveImpl implements WebConnectServiceSolve {
	// ********************************************************************************
	// ==========================�����²����(�ر�ע���Ѿ���ע����ڶ��㣬��˵ڶ���Ķ�����ע������)
	private WebConnectService webConnectService;
	// ********************************************************************************
	// ==========================��Ҫ�õ���Service����====================================
	@Resource
	private CustomeronlineService customeronlineService;
	@Resource
	private CustomerserviceonlineService customerserviceonlineService;
	@Resource
	private TerminalService terminalService;

	public void setCustomeronlineService(CustomeronlineService customeronlineService) {
		this.customeronlineService = customeronlineService;
	}

	public void setCustomerserviceonlineService(CustomerserviceonlineService customerserviceonlineService) {
		this.customerserviceonlineService = customerserviceonlineService;
	}

	public void setTerminalService(TerminalService terminalService) {
		this.terminalService = terminalService;
	}

	// ********************************************************************************
	// ==========================����Msg����=============================================
	/**
	 * ����²㴫�ݵ�msg����
	 * ��������Ϣ���²㴫�ݵĽӿ�
	 */
	@Override
	public void msgSubmit(Msg msg,WebConnectService webConnectService) {
		System.out.println("�����㣺msgSubmit");
		this.webConnectService = webConnectService;
		//�����²����
		Terminal terminal = null;
		// 1.��һ��������Ϣ������ն�
		terminal = msgAnalysis(msg);
		// 2.�ڶ�������Ϣ����ת��
		msgHandle(msg, terminal);
	}

	/**
	 * ����msg��msgtype ͨ��ȡ���ж���Ϣ�Ľ��շ�����ն�Terminal�� �����ͨ��Terminal���󷵻� 1.����״̬���жϽ��շ�
	 * 2.���ݽ��շ�����Ӧ���߱���Termianl id 3.����Terminal��������÷���
	 */
	@Override
	public Terminal msgAnalysis(Msg msg) {
		System.out.println("�����㣺msgAnalysis");
		Terminal terminal = null;
		// ������Ϣ�Ľ��շ�
		Integer receive = (msg.getMsgtype().getId()) % 1000;
		// �����Ϣ�����ݶ���
		Customer customer = msg.getCustomer();
		Customerservice customerservice = msg.getCustomerservice();
		// ��֤��Ϣ�����Ƿ����(��ֹȡ��ֵ)
		Integer customerid = null;
		Integer serviceid = null;
		if (customer != null) {
			customerid = msg.getCustomer().getCtId();
		}
		if (customerservice != null) {
			serviceid = msg.getCustomerservice().getCsId();
		}
		// ��֤��Ϣ�����Ƿ�����
		
		
		// ��֤��Ϣ���շ��������ն�����
		if ((receive >= 100) && (receive < 200)) {
			// �������շ�Ϊ�ͷ�
			Customerserviceonline customerserviceonline = customerserviceonlineService.get(serviceid);
			terminal = customerserviceonlineService.get(serviceid).getTerminal();
			/**
			 * ����Ϊʲôһ��Ҫ��������,����������ͻᱨ��
			 * �����ص�����:Terminal����ʱ���ض���Ϊnull
			 * ��Terminal��TerminalTypeʱ,��ΪT6erminalΪ�����Ի�ȡ����
			 */
			System.out.println("������:msgAnalysis:getTerminal=" + terminal.getTerminaltype());
		} else if ((receive >= 200) && (receive < 300)) {
			// �������շ�Ϊ�ͻ�
			terminal = customeronlineService.get(customerid).getTerminal();
		} else if ((receive >= 300) && (receive < 400)) {
			// �������շ�Ϊ������
		} else if ((receive >= 400) && (receive < 500)) {
			// ����δʶ��״̬��
		} else {
			System.out.println("msgtype Error");
		}
		return terminal;
	}

	/**
	 * ��Ϣ���� ����Terminal�ҵ���Ӧ�նˣ����ö�Ӧ�ķ����������Ϣת��
	 */
	@Override
	public void msgHandle(Msg msg, Terminal terminal) {
		System.out.println("�����㣺msgHandle");
		Integer terminaltypeid = null;
		// ���Ƿ�������������Ϣ
		if (terminal != null) {
			System.out.println("�����㣺msgHandle:terminal=" + terminal.getId());
			TerminaltypeService terminaltypeService;
			terminaltypeid = terminal.getTerminaltype().getId();
			switch (terminaltypeid) {
			// �������շ�Ϊweb��
			case 100:
				// ����Ϣ�����²�
				// Web����ʹ��WebSocket�ķ�������webConnectService
				System.out.println("�����㣺sendMsg");
				webConnectService.sendMsg(msg, terminal);
				break;
			// �������շ�Ϊ�ƶ���
			case 101:
				break;
			default:
				break;
			}
		}
		System.out.println("�����㣺msgHandleִ�н���");
	}

	/**
	 * �����������õ�submit����
	 */
	@Override
	public void msgSubmit(Msg msg) {
		Terminal terminal = null;
		// 1.��һ��������Ϣ������ն�
		terminal = msgAnalysis(msg);
		// 2.�ڶ�������Ϣ����ת��
		msgHandle(msg, terminal);
	}

}
