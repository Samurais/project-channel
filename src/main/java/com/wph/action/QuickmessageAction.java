package com.wph.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Quickmessage;
import com.wph.entities.Robotknowledgebase;

@Controller("quickmessageAction")
@Scope("prototype")
public class QuickmessageAction extends BaseAction<Quickmessage> {
	// ********************************************************************************
	// ==================================�ͷ�ID=========================================
	private Integer serviceid;

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}

	// ********************************************************************************
	// ===============================���ݿ��ҳ��ѯ======================================

	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", quickmessageService.getCount(serviceid));
		jsonMap.put("rows", quickmessageService.pageQuery(limit, offset, search, serviceid));
		return "jsonMap";
	}

	// ********************************************************************************
	// =================================save����========================================
	public String save() {
		model.setCustomerservice(customerserviceService.get(serviceid));
		quickmessageService.save(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ===============================����===========================================
	public String update() {
		model.setCustomerservice(customerserviceService.get(serviceid));
		quickmessageService.update(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ===============================ɾ��==============================================
	public String delete() {
		quickmessageService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}
}
