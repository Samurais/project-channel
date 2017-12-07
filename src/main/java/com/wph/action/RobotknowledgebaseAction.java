package com.wph.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Robotknowledgebase;
import com.wph.entities.json.RobotknowledgebaseJSON;

@Controller("robotknowledgebaseAction")
@Scope("prototype")
public class RobotknowledgebaseAction extends BaseAction<RobotknowledgebaseJSON> {
	// ********************************************************************************
	// ===============================数据库分页查询======================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", robotknowledgebaseService.getCount(model.getCompanyid()));
		jsonMap.put("rows", robotknowledgebaseService.pageQuery(limit, offset, search, model.getCompanyid()));
		return "jsonMap";
	}

	// ********************************************************************************
	// =================================save方法========================================
	public String save() {
		System.out.println(model.getCompanyid());
		robotknowledgebaseService.saveByCompany(model, model.getCompanyid());
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ===============================更新===========================================
	public String update() {
		Robotknowledgebase base = robotknowledgebaseService.jsonToKnowledge(model);
		robotknowledgebaseService.update(base);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ===============================删除==============================================
	public String delete() {
		robotknowledgebaseService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

}
