package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Category;
import com.wph.entities.json.CategoryJSON;
import com.wph.entities.json.ProductJSON;

public interface CategoryService extends BaseService<Category> {

	public BigInteger getCount(Integer companyid);
	
	public List<CategoryJSON> pageQuery(Integer companyid, Integer limit, Integer offset, String search);

	public void update(Category model, Integer companyid);

	public void save(Category model, Integer companyid);

	public void deleteByIds(String ids);

	public boolean idValid(Integer id);
	
	

}
