package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Administrator;
import com.wph.entities.Company;
import com.wph.entities.json.CompanyConversationJSON;
import com.wph.entities.json.CompanyJSON;

public interface AdministratorService extends BaseService<Administrator> {

	// 获得所有公司的当天会话量
	public List<CompanyConversationJSON> getCompanyConversationCount();

	// 登陆验证
	public String loginValidate(Integer id, String password);

	//带公司验证的数量统计
	public Long getCount();
	
	// 带公司验证的页面请求
	public List<Company> pageQuery(Integer limit, Integer offset, String search);
	
}
