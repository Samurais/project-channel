package com.wph.information.handle;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

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
import com.wph.util.impl.SpringUtils;

@Controller("infHandle")
public class InfHandleImpl implements InfHandle {
	@Resource
	InfHandleService infHandleService;

	public void setInfHandleService(InfHandleService infHandleService) {
		this.infHandleService = infHandleService;
	}


	/**
	 * 供服务器调用的submit方法
	 */
	public void msgSubmit(Msg msg) {
		Terminal terminal = null;
		// 1.第一步分析消息，获得终端
		terminal = infHandleService.msgAnalysis(msg);
		// 2.第二步，消息进行转发
		infHandleService.msgHandle(msg, terminal);
	}
}
