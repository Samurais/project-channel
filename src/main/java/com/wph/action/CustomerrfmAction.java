package com.wph.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Customerrfm;
import com.wph.entities.json.RfmScatterJSON;

@Controller("customerrfmAction")
@Scope("prototype")
public class CustomerrfmAction extends BaseAction<Customerrfm> {
	// ********************************************************************************
	// =================================������˾����=====================================
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	// ********************************************************************************
	// =================================�ͻ�RFM��¼��ҳ��ѯ================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", customerrfmService.getCount(companyid));
		jsonMap.put("rows", customerrfmService.pageQuery(limit, offset, search, companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// =================================�ͻ�RFMɢ��ͼ��ѯ=================================
	public String rfmScatter() {
		jsonList = new ArrayList<RfmScatterJSON>();
		jsonList.add(customerrfmService.getRfmScatter(companyid));
		return "jsonList";
	}
}
