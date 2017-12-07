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
	// ================================companyid部分=====================================
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	// ********************************************************************************
	// ================================ID重复验证部分=====================================
	// 查看ID是否重复
	public String idValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", customerservicerightService.idValid(model.getId()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================分页查询========================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", customerservicerightService.getCount(companyid));
		jsonMap.put("rows", customerservicerightService.pageQuery(limit, offset, search, companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// =================================save方法========================================
	public String save() {
		model.setCompany(companyService.get(companyid));
		customerservicerightService.save(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==========================修改对应的onEditableSave方法=============================
	public String editUpdate() {
		model.setCompany(companyService.get(companyid));
		customerservicerightService.editUpdate(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================delete方法======================================
	public String deleteByIds() {
		customerservicerightService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
}
