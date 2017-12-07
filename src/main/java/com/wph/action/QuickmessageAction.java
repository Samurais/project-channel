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
	// ==================================客服ID=========================================
	private Integer serviceid;

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}

	// ********************************************************************************
	// ===============================数据库分页查询======================================

	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", quickmessageService.getCount(serviceid));
		jsonMap.put("rows", quickmessageService.pageQuery(limit, offset, search, serviceid));
		return "jsonMap";
	}

	// ********************************************************************************
	// =================================save方法========================================
	public String save() {
		model.setCustomerservice(customerserviceService.get(serviceid));
		quickmessageService.save(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ===============================更新===========================================
	public String update() {
		model.setCustomerservice(customerserviceService.get(serviceid));
		quickmessageService.update(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ===============================删除==============================================
	public String delete() {
		quickmessageService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}
}
