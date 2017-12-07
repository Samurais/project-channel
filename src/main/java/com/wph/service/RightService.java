package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Right;
import com.wph.entities.json.RightJSON;

public interface RightService extends BaseService<Right> {
	public void saveJSON(RightJSON json);

	public BigInteger getCount(Integer companyid);

	public List<RightJSON> pageQuery(Integer limit, Integer offset, String search, Integer companyid);

	public void editUpdate(RightJSON json);
	
	public void deleteByIds(String ids);
	
	public boolean idValid(Integer id);
}
