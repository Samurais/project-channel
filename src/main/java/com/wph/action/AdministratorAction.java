package com.wph.action;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Administrator;
import com.wph.service.AdministratorService;
import com.wph.service.impl.BaseServiceImpl;

@Controller("administratorAction")
@Scope("prototype")
public class AdministratorAction extends BaseAction<Administrator> {

	public String getCompanyConversationCount() {
		jsonList = new ArrayList<Object>();
		jsonList = administratorService.getCompanyConversationCount();
		return "jsonList";
	}
	// ********************************************************************************
	// ==================================账号登陆验证=====================================
	private Integer loginid;
	private String loginpassword;

	public void setLoginid(Integer loginid) {
		this.loginid = loginid;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}

	public String loginValidate() {
		// 有三种返回状态 1.success 2.false 3.noexist
		// 保存验证数据
		String result = administratorService.loginValidate(loginid, loginpassword);
		inputStream = new ByteArrayInputStream(result.getBytes());
		System.out.println("loginValidate");
		return "stream";
	}

	// ********************************************************************************
	// ==================================分页查询========================================
	public String companyPageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", administratorService.getCount());
		jsonMap.put("rows", administratorService.pageQuery(limit, offset, search));
		return "jsonMap";
	}
}
