package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Userrole;
import com.wph.entities.json.RoleSelectJSON;
import com.wph.entities.json.UserroleJSON;

public interface UserroleService extends BaseService<Userrole> {
	public BigInteger getCount(Integer companyid);

	public List<UserroleJSON> pageQuery(Integer limit, Integer offset, String search, Integer companyid);

	public List<UserroleJSON> getServiceRole(Integer companyid);
	
	public List<RoleSelectJSON> getRoleSelectJSON(Integer companyid);
}
