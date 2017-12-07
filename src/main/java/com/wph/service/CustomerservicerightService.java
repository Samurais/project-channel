package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Customerserviceright;
import com.wph.entities.json.RightJSON;

public interface CustomerservicerightService extends BaseService<Customerserviceright> {
	public Long getCount(Integer companyid);

	public List<Customerserviceright> pageQuery(Integer limit, Integer offset, String search, Integer companyid);

	public void editUpdate(Customerserviceright csr);

	public void deleteByIds(String ids);

	public boolean idValid(Integer id);
}
