package com.wph.action;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Msg;

@Controller("msgAction")
@Scope("prototype")
public class MsgAction extends BaseAction<Msg> {
	// ********************************************************************************
	// =================================辨明公司号码=====================================
	private Integer companyid;
	
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	// ********************************************************************************
	// =================================消息记录分页查询===================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", msgService.getCount(companyid));
		jsonMap.put("rows", msgService.pageQuery(limit, offset, search,companyid));
		return "jsonMap";
	}
	
}
