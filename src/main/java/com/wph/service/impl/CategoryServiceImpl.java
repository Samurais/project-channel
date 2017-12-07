package com.wph.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.entities.Category;
import com.wph.entities.Company;
import com.wph.entities.json.CategoryJSON;
import com.wph.entities.json.service.CategoryJSONService;
import com.wph.entities.json.service.ProductJSONService;
import com.wph.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

	@Override
	public BigInteger getCount(Integer companyid) {
		String sql = "select count(*) from category c where c.company_id =:companyid";

		return (BigInteger) getSession().createSQLQuery(sql).setInteger("companyid", companyid).uniqueResult();
	}

	@Override
	public List<CategoryJSON> pageQuery(Integer companyid, Integer limit, Integer offset, String search) {
		List<Object[]> categoryList = null;
		if (search == null) {
			String sql = "select * from category c where c.company_id=:companyid";
			categoryList = getSession().createSQLQuery(sql).setInteger("companyid", companyid).setFirstResult(offset)
					.setMaxResults(limit).list();
		} else {
			String sql = "select * from category c where c.company_id =:companyid and c.type like:search";
			categoryList = getSession().createQuery(sql).setInteger("companyid", companyid)
					.setString("search", "%" + search + "%").setFirstResult(offset).setMaxResults(limit).list();
		}

		return CategoryJSONService.categorytoJSON(categoryList);
	}

	@Override
	public void update(Category model, Integer companyid) {
		Company company = (Company) getSession().get(Company.class, companyid);
		model.setCompany(company);
		update(model);

	}

	@Override
	public void save(Category model, Integer companyid) {
		Company company = (Company) getSession().get(Company.class, companyid);
		model.setCompany(company);
		save(model);
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Category c where c.id in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();

	}

	@Override
	public boolean idValid(Integer id) {
		String hql = "select count(*) from Category c where c.id =" + id;
		Long count = (Long) getSession().createQuery(hql).uniqueResult();
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

}
