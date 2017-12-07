package com.wph.service;

import com.wph.entities.Company;

public interface CompanyService extends BaseService<Company> {
	public String loginValidate(Integer id,String password);
	
	public String getBasePath(Integer companyid);
}
