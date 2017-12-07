package com.wph.action;

import java.io.ByteArrayInputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Company;

@Controller("companyAction")
@Scope("prototype")
public class CompanyAction extends BaseAction<Company> {
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
		String result = companyService.loginValidate(loginid, loginpassword);
		inputStream = new ByteArrayInputStream(result.getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ===============================获得公司的合理窗口安排设置=============================
	
}
