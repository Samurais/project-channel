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
	// ================================��˾ID����=====================================
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	// ********************************************************************************
	// ================================ID�ظ���֤����=====================================
	// �鿴ID�Ƿ��ظ�
	public String idValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", customerserviceroleService.idValid(model.getId()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================��ҳ��ѯ========================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", customerserviceroleService.getCount(companyid));
		jsonMap.put("rows", customerserviceroleService.pageQuery(limit, offset, search, companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================��ҳ��ѯ========================================
	public String pageQueryJoinRight() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", customerserviceroleService.getCount(companyid));
		jsonMap.put("rows", customerserviceroleService.pageQueryJoinRight(limit, offset, search, companyid));
		return "jsonMap";
	}
	// ********************************************************************************
	// =================================save����========================================

	public String save() {
		model.setCompany(companyService.get(companyid));
		customerserviceroleService.save(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==========================�޸Ķ�Ӧ��onEditableSave����=============================
	public String editUpdate() {
		model.setCompany(companyService.get(companyid));
		customerserviceroleService.editUpdate(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================delete����======================================
	public String deleteByIds() {
		customerserviceroleService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==========================���ݹ�˾ID�������Ȩ�޷���==================================
	public String getRoleRight() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", customerservicerightService.getCount(companyid));
		jsonMap.put("rows", customerserviceroleService.getRoleRight(model.getId(), companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// =========================��ȡ���еĽ�ɫ(Զ��������ʹ��)================================
	public String getTotalRole() {
		jsonList = new ArrayList<CustomerserviceroleSelectJSON>();
		jsonList = customerserviceroleService.getRoleSelectJSON(companyid);
		return "jsonList";
	}

	// ********************************************************************************
	// ===============================��ȡ�����˵Ľ�ɫ��Ϣ==================================
	public String getServiceRole() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", userroleService.getCount(companyid));
		jsonMap.put("rows", userroleService.pageQuery(limit, offset, search, companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==============================�޸�Ȩ�޷���=========================================
	public String updateRightByIds() {
		customerserviceroleService.updateRightByIds(model.getId(), ids);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}
}
