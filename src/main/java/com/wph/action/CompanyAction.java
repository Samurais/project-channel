package com.wph.action;

import java.io.ByteArrayInputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Company;

@Controller("companyAction")
@Scope("prototype")
public class CompanyAction extends BaseAction<Company> {
	// ********************************************************************************
	// ==================================�˺ŵ�½��֤=====================================
	private Integer loginid;
	private String loginpassword;

	public void setLoginid(Integer loginid) {
		this.loginid = loginid;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}

	public String loginValidate() {
		// �����ַ���״̬ 1.success 2.false 3.noexist
		// ������֤����
		String result = companyService.loginValidate(loginid, loginpassword);
		inputStream = new ByteArrayInputStream(result.getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ===============================��ù�˾�ĺ����ڰ�������=============================
	
}
