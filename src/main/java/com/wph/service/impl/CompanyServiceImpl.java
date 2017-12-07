package com.wph.service.impl;

import org.springframework.stereotype.Service;

import com.wph.entities.Company;
import com.wph.service.CompanyService;

@Service("companyService")
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {

	@Override
	public String loginValidate(Integer id, String password) {
		Company company = get(id);
		if (company != null) {
			if (company.getCpPassword().equals(password)) {
				return "success";
			}else{
				return "false";
			}
		}else{
			return "noexist";
		}
	}

	@Override
	public String getBasePath(Integer companyid) {
		Company company = get(companyid);
		String path = company.getCpKonwledgebasepath();
		return path;
	}

}
