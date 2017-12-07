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
	// ================================ID重复验证部分=====================================
	// 查看ID是否重复
	public String idValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", rightService.idValid(model.getId()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================分页查询========================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", rightService.getCount(model.getCompanyid()));
		jsonMap.put("rows", rightService.pageQuery(limit, offset, search, model.getCompanyid()));
		return "jsonMap";
	}

	// ********************************************************************************
	// =================================save方法========================================
	public String save(){
		rightService.saveJSON(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==========================修改对应的onEditableSave方法=============================
	public String editUpdate() {
		rightService.editUpdate(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================delete方法======================================
	public String deleteByIds() {
		rightService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
}
