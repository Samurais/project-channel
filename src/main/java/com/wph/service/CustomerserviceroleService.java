package com.wph.service;

import java.util.List;

import com.wph.entities.Customerservicerole;
import com.wph.entities.json.CustomerservicerightroleJSON;
import com.wph.entities.json.CustomerserviceroleJSON;
import com.wph.entities.json.CustomerserviceroleSelectJSON;

public interface CustomerserviceroleService extends BaseService<Customerservicerole> {

	public Long getCount(Integer companyid);

	public List<Customerservicerole> pageQuery(Integer limit, Integer offset, String search, Integer companyid);

	public List<CustomerserviceroleJSON> pageQueryJoinRight(Integer limit, Integer offset, String search,
			Integer companyid);

	public void editUpdate(Customerservicerole role);

	public void deleteByIds(String ids);

	public boolean idValid(Integer id);

	public List<CustomerservicerightroleJSON> getRoleRight(Integer roleid, Integer companyid);

	public void updateRightByIds(Integer id, String ids);

	public List<CustomerserviceroleSelectJSON> getRoleSelectJSON(Integer companyid);
}
