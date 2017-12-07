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
	// ==========================保存下层对象(特别注意已经被注射入第二层，因此第二层的对象不能注射这里)
	private WebConnectService webConnectService;
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

	// ********************************************************************************
	// ==========================处理Msg对象=============================================
	/**
	 * 获得下层传递的msg对象
	 * 服务器消息往下层传递的接口
	 */
	@Override
	public void msgSubmit(Msg msg,WebConnectService webConnectService) {
		System.out.println("第三层：msgSubmit");
		this.webConnectService = webConnectService;
		//保存下层对象
		Terminal terminal = null;
		// 1.第一步分析消息，获得终端
		terminal = msgAnalysis(msg);
		// 2.第二步，消息进行转发
		msgHandle(msg, terminal);
	}

	/**
	 * 分析msg的msgtype 通过取余判断消息的接收方获得终端Terminal类 将结果通过Terminal对象返回 1.根据状态码判断接收方
	 * 2.根据接收方从相应在线表获得Termianl id 3.根据Terminal最后分类调用方法
	 */
	@Override
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
			Customerserviceonline customerserviceonline = customerserviceonlineService.get(serviceid);
			terminal = customerserviceonlineService.get(serviceid).getTerminal();
			/**
			 * 这里为什么一定要切入事务,不切入事务就会报错
			 * 懒加载的问题:Terminal是延时加载对象为null
			 * 当Terminal的TerminalType时,因为T6erminal为空所以获取不到
			 */
			System.out.println("第三层:msgAnalysis:getTerminal=" + terminal.getTerminaltype());
		} else if ((receive >= 200) && (receive < 300)) {
			// 表明接收方为客户
			terminal = customeronlineService.get(customerid).getTerminal();
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
	@Override
	public void msgHandle(Msg msg, Terminal terminal) {
		System.out.println("第三层：msgHandle");
		Integer terminaltypeid = null;
		// 若是发给服务器的消息
		if (terminal != null) {
			System.out.println("第三层：msgHandle:terminal=" + terminal.getId());
			TerminaltypeService terminaltypeService;
			terminaltypeid = terminal.getTerminaltype().getId();
			switch (terminaltypeid) {
			// 表明接收方为web端
			case 100:
				// 将消息传递下层
				// Web对象使用WebSocket的服务层对象webConnectService
				System.out.println("第三层：sendMsg");
				webConnectService.sendMsg(msg, terminal);
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

	/**
	 * 供服务器调用的submit方法
	 */
	@Override
	public void msgSubmit(Msg msg) {
		Terminal terminal = null;
		// 1.第一步分析消息，获得终端
		terminal = msgAnalysis(msg);
		// 2.第二步，消息进行转发
		msgHandle(msg, terminal);
	}

}
