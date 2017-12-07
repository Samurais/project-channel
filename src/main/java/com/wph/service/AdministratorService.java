package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Administrator;
import com.wph.entities.Company;
import com.wph.entities.json.CompanyConversationJSON;
import com.wph.entities.json.CompanyJSON;

public interface AdministratorService extends BaseService<Administrator> {

	// ������й�˾�ĵ���Ự��
	public List<CompanyConversationJSON> getCompanyConversationCount();

	// ��½��֤
	public String loginValidate(Integer id, String password);

	//����˾��֤������ͳ��
	public Long getCount();
	
	// ����˾��֤��ҳ������
	public List<Company> pageQuery(Integer limit, Integer offset, String search);
	
}
