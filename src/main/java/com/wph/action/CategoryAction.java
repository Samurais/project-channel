package com.wph.action;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Category;

@Controller("categoryAction")
@Scope("prototype")
public class CategoryAction extends BaseAction<Category> {
	// ********************************************************************************
	// ================================ID�ظ���֤����=====================================
	//�鿴ID�Ƿ��ظ�
	public String idValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", categoryService.idValid(model.getId()));
		return "jsonMap";
	}
	
	// ********************************************************************************
	// ==================================��ҳ��ѯ����=====================================
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows", categoryService.pageQuery(companyid, limit, offset, search));
		jsonMap.put("total", categoryService.getCount(companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==========================�޸Ķ�Ӧ��onEditableSave����=============================
	public String editUpdate() {
		categoryService.update(model, companyid);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================save����========================================
	public String save() {
		categoryService.save(model, companyid);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}
	// ********************************************************************************
	// =================================delete����======================================
	public String deleteByIds() {
		System.out.println(ids);
		categoryService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}

}
