package com.wph.action;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.json.RoleJSON;
import com.wph.entities.json.RoleSelectJSON;

@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction<RoleJSON> {
	// ********************************************************************************
	// ================================ID�ظ���֤����=====================================
	// �鿴ID�Ƿ��ظ�
	public String idValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", roleService.idValid(model.getId()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================��ҳ��ѯ========================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", roleService.getCount(model.getCompanyid()));
		jsonMap.put("rows", roleService.pageQuery(limit, offset, search, model.getCompanyid()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================��ҳ��ѯ========================================
	public String pageQueryJoinRight() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", roleService.getCount(model.getCompanyid()));
		jsonMap.put("rows", roleService.pageQueryJoinRight(limit, offset, search, model.getCompanyid()));
		return "jsonMap";
	}
	// ********************************************************************************
	// =================================save����========================================

	public String save() {
		roleService.saveJSON(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==========================�޸Ķ�Ӧ��onEditableSave����=============================
	public String editUpdate() {
		roleService.editUpdate(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================delete����======================================
	public String deleteByIds() {
		roleService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =====================���ݽ�ɫID��ý�ɫ��Ӧ��Ȩ�޷���==================================
	public String getRoleRight() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", rightService.getCount(model.getCompanyid()));
		jsonMap.put("rows", roleService.getRoleRight(model.getId(), model.getCompanyid()));
		return "jsonMap";
	}

	// ********************************************************************************
	// =====================���ݽ�ɫID��ý�ɫ��Ӧ��Ȩ�޷���==================================
	public String editRoleRight() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", rightService.getCount(model.getCompanyid()));
		jsonMap.put("rows", roleService.getRoleRight(model.getId(), model.getCompanyid()));
		return "jsonMap";
	}
	// ********************************************************************************
	// =========================��ȡ���еĽ�ɫ(Զ��������ʹ��)================================
	public String getTotalRole(){
		Integer companyid = model.getCompanyid();
		jsonList = new ArrayList<RoleSelectJSON>();
		jsonList = userroleService.getRoleSelectJSON(companyid);
		return "jsonList";
	}
	
	// ********************************************************************************
	// ===============================��ȡ�����˵Ľ�ɫ��Ϣ==================================
	public String getServiceRole() {
		Integer companyid = model.getCompanyid();
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", userroleService.getCount(companyid));
		jsonMap.put("rows", userroleService.pageQuery(limit, offset, search, companyid));
		return "jsonMap";
	}
}
