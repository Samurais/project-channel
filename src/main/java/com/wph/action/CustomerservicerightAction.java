package com.wph.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Customerserviceright;

@Controller("customerservicerightAction")
@Scope("prototype")
public class CustomerservicerightAction extends BaseAction<Customerserviceright> {
	// ********************************************************************************
	// ================================companyid����=====================================
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	// ********************************************************************************
	// ================================ID�ظ���֤����=====================================
	// �鿴ID�Ƿ��ظ�
	public String idValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", customerservicerightService.idValid(model.getId()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================��ҳ��ѯ========================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", customerservicerightService.getCount(companyid));
		jsonMap.put("rows", customerservicerightService.pageQuery(limit, offset, search, companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// =================================save����========================================
	public String save() {
		model.setCompany(companyService.get(companyid));
		customerservicerightService.save(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==========================�޸Ķ�Ӧ��onEditableSave����=============================
	public String editUpdate() {
		model.setCompany(companyService.get(companyid));
		customerservicerightService.editUpdate(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================delete����======================================
	public String deleteByIds() {
		customerservicerightService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
}
