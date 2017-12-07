package com.wph.action;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Msg;

@Controller("msgAction")
@Scope("prototype")
public class MsgAction extends BaseAction<Msg> {
	// ********************************************************************************
	// =================================������˾����=====================================
	private Integer companyid;
	
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	// ********************************************************************************
	// =================================��Ϣ��¼��ҳ��ѯ===================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", msgService.getCount(companyid));
		jsonMap.put("rows", msgService.pageQuery(limit, offset, search,companyid));
		return "jsonMap";
	}
	
}
