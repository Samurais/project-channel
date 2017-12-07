package com.wph.information.handle;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wph.connect.service.WebConnectService;
import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.entities.Customerserviceonline;
import com.wph.entities.Msg;
import com.wph.entities.Terminal;
import com.wph.information.websocket.WebSocketPacking;
import com.wph.service.CustomeronlineService;
import com.wph.service.CustomerserviceonlineService;
import com.wph.service.TerminalService;
import com.wph.service.TerminaltypeService;
import com.wph.util.impl.SpringUtils;

@Service("infHandleService")
public class InfHandleServiceImpl implements InfHandleService {
	// ********************************************************************************
	// ==========================需要用到的Service服务====================================
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

	/**
	 * 分析msg的msgtype 通过取余判断消息的接收方获得终端Terminal类 将结果通过Terminal对象返回 1.根据状态码判断接收方
	 * 2.根据接收方从相应在线表获得Termianl id 3.根据Terminal最后分类调用方法
	 */
	public Terminal msgAnalysis(Msg msg) {
		System.out.println("第三层：msgAnalysis");
		Terminal terminal = null;
		// 分析消息的接收方
		Integer receive = (msg.getMsgtype().getId()) % 1000;
		// 获得消息的内容对象
		Customer customer = msg.getCustomer();
		Customerservice customerservice = msg.getCustomerservice();
		// 验证消息对象是否存在(防止取空值)
		Integer customerid = null;
		Integer serviceid = null;
		if (customer != null) {
			customerid = msg.getCustomer().getCtId();
		}
		if (customerservice != null) {
			serviceid = msg.getCustomerservice().getCsId();
		}
		// 验证消息对象是否在线

		// 验证消息接收方，返回终端类型
		if ((receive >= 100) && (receive < 200)) {
			// 表明接收方为客服
			Integer terminalid = customerserviceonlineService.getTerminal(serviceid);
			if (terminalid != null) {
				terminal = terminalService.get(terminalid);
			}
		} else if ((receive >= 200) && (receive < 300)) {
			// 表明接收方为客户
			Integer terminalid = customeronlineService.getTerminal(customerid);
			if (terminalid != null) {
				terminal = terminalService.get(terminalid);
			}
		} else if ((receive >= 300) && (receive < 400)) {
			// 表明接收方为服务器
		} else if ((receive >= 400) && (receive < 500)) {
			// 表明未识别状态码
		} else {
			System.out.println("msgtype Error");
		}
		return terminal;
	}

	/**
	 * 消息处理 根据Terminal找到对应终端，调用对应的方法，完成消息转发
	 */
	public void msgHandle(Msg msg, Terminal terminal) {
		System.out.println("第三层：msgHandle");
		Integer terminaltypeid = null;
		// 若是发给服务器的消息
		if (terminal != null) {
			System.out.println("第三层：msgHandle:terminal=" + terminal.getId());
			terminaltypeid = terminal.getTerminaltype().getId();
			switch (terminaltypeid) {
			// 表明接收方为web端
			case 100:
				// 将消息传递下层
				// Web对象使用WebSocket的服务层对象webConnectService
				System.out.println("第三层：sendMsg");
				WebSocketPacking webSocketPacking = (WebSocketPacking) SpringUtils.getBean("webSocketPacking");
				webSocketPacking.sendMsg(msg, terminal);
				break;
			// 表明接收方为移动端
			case 101:
				break;
			default:
				break;
			}
		}
		System.out.println("第三层：msgHandle执行结束");
	}

}
