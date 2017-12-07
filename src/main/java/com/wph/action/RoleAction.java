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
	// ================================ID重复验证部分=====================================
	// 查看ID是否重复
	public String idValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", roleService.idValid(model.getId()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================分页查询========================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", roleService.getCount(model.getCompanyid()));
		jsonMap.put("rows", roleService.pageQuery(limit, offset, search, model.getCompanyid()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================分页查询========================================
	public String pageQueryJoinRight() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", roleService.getCount(model.getCompanyid()));
		jsonMap.put("rows", roleService.pageQueryJoinRight(limit, offset, search, model.getCompanyid()));
		return "jsonMap";
	}
	// ********************************************************************************
	// =================================save方法========================================

	public String save() {
		roleService.saveJSON(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==========================修改对应的onEditableSave方法=============================
	public String editUpdate() {
		roleService.editUpdate(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================delete方法======================================
	public String deleteByIds() {
		roleService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =====================根据角色ID获得角色对应的权限方法==================================
	public String getRoleRight() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", rightService.getCount(model.getCompanyid()));
		jsonMap.put("rows", roleService.getRoleRight(model.getId(), model.getCompanyid()));
		return "jsonMap";
	}

	// ********************************************************************************
	// =====================根据角色ID获得角色对应的权限方法==================================
	public String editRoleRight() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", rightService.getCount(model.getCompanyid()));
		jsonMap.put("rows", roleService.getRoleRight(model.getId(), model.getCompanyid()));
		return "jsonMap";
	}
	// ********************************************************************************
	// =========================获取所有的角色(远程下拉框使用)================================
	public String getTotalRole(){
		Integer companyid = model.getCompanyid();
		jsonList = new ArrayList<RoleSelectJSON>();
		jsonList = userroleService.getRoleSelectJSON(companyid);
		return "jsonList";
	}
	
	// ********************************************************************************
	// ===============================获取所有人的角色信息==================================
	public String getServiceRole() {
		Integer companyid = model.getCompanyid();
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", userroleService.getCount(companyid));
		jsonMap.put("rows", userroleService.pageQuery(limit, offset, search, companyid));
		return "jsonMap";
	}
}
