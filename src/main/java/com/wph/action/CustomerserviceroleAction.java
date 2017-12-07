package com.wph.action;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Customerservicerole;
import com.wph.entities.json.CustomerserviceroleSelectJSON;

@Controller("customerserviceroleAction")
@Scope("prototype")
public class CustomerserviceroleAction extends BaseAction<Customerservicerole> {
	// ********************************************************************************
	// ================================公司ID部分=====================================
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	// ********************************************************************************
	// ================================ID重复验证部分=====================================
	// 查看ID是否重复
	public String idValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", customerserviceroleService.idValid(model.getId()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================分页查询========================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", customerserviceroleService.getCount(companyid));
		jsonMap.put("rows", customerserviceroleService.pageQuery(limit, offset, search, companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================分页查询========================================
	public String pageQueryJoinRight() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", customerserviceroleService.getCount(companyid));
		jsonMap.put("rows", customerserviceroleService.pageQueryJoinRight(limit, offset, search, companyid));
		return "jsonMap";
	}
	// ********************************************************************************
	// =================================save方法========================================

	public String save() {
		model.setCompany(companyService.get(companyid));
		customerserviceroleService.save(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==========================修改对应的onEditableSave方法=============================
	public String editUpdate() {
		model.setCompany(companyService.get(companyid));
		customerserviceroleService.editUpdate(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================delete方法======================================
	public String deleteByIds() {
		customerserviceroleService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==========================根据公司ID获得所有权限方法==================================
	public String getRoleRight() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", customerservicerightService.getCount(companyid));
		jsonMap.put("rows", customerserviceroleService.getRoleRight(model.getId(), companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// =========================获取所有的角色(远程下拉框使用)================================
	public String getTotalRole() {
		jsonList = new ArrayList<CustomerserviceroleSelectJSON>();
		jsonList = customerserviceroleService.getRoleSelectJSON(companyid);
		return "jsonList";
	}

	// ********************************************************************************
	// ===============================获取所有人的角色信息==================================
	public String getServiceRole() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", userroleService.getCount(companyid));
		jsonMap.put("rows", userroleService.pageQuery(limit, offset, search, companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==============================修改权限方法=========================================
	public String updateRightByIds() {
		customerserviceroleService.updateRightByIds(model.getId(), ids);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}
}
