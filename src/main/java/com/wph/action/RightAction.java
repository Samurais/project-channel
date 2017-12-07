package com.wph.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.json.RightJSON;

@Controller("rightAction")
@Scope("prototype")
public class RightAction extends BaseAction<RightJSON> {
	// ********************************************************************************
	// ================================ID�ظ���֤����=====================================
	// �鿴ID�Ƿ��ظ�
	public String idValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", rightService.idValid(model.getId()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================��ҳ��ѯ========================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", rightService.getCount(model.getCompanyid()));
		jsonMap.put("rows", rightService.pageQuery(limit, offset, search, model.getCompanyid()));
		return "jsonMap";
	}

	// ********************************************************************************
	// =================================save����========================================
	public String save(){
		rightService.saveJSON(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==========================�޸Ķ�Ӧ��onEditableSave����=============================
	public String editUpdate() {
		rightService.editUpdate(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================delete����======================================
	public String deleteByIds() {
		rightService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
}
