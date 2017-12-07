package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Role;
import com.wph.entities.json.RoleJSON;
import com.wph.entities.json.RoleRightJSON;

public interface RoleService extends BaseService<Role> {
	public void saveJSON(RoleJSON json);

	public BigInteger getCount(Integer companyid);

	public List<RoleJSON> pageQuery(Integer limit, Integer offset, String search, Integer companyid);
	
	public List<RoleJSON> pageQueryJoinRight(Integer limit, Integer offset, String search, Integer companyid);

	public void editUpdate(RoleJSON json);

	public void deleteByIds(String ids);

	public boolean idValid(Integer id);
	
	public List<RoleRightJSON> getRoleRight(Integer roleid,Integer companyid);
}
