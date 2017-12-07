package com.wph.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.entities.Administrator;
import com.wph.entities.Company;
import com.wph.entities.Customer;
import com.wph.entities.json.CompanyConversationJSON;
import com.wph.entities.json.CompanyJSON;
import com.wph.service.AdministratorService;

@Service("administratorService")
public class AdministratorServiceImpl extends BaseServiceImpl<Administrator> implements AdministratorService {

	@Override
	public List<CompanyConversationJSON> getCompanyConversationCount() {
		List<CompanyConversationJSON> jsonlist = new ArrayList<CompanyConversationJSON>();
		String sql = "select c.cp_name,c.cp_id from company c";
		List<Object[]> obs = getSession().createSQLQuery(sql).list();
		for (Object[] ob : obs) {
			CompanyConversationJSON json = new CompanyConversationJSON();
			if (ob[0] != null) {
				json.setName((String) ob[0]);
			} else {
				json.setName(ob[1].toString());
			}
			String sql2 = "select count(*) from conversation c where to_days(c.begintime) = to_days(now())"
					+ " and c.costomerService_id in(select o.cs_id from customerservice o where o.company_id = :id)";

			Object object = getSession().createSQLQuery(sql2).setInteger("id", 1122).uniqueResult();

			BigInteger count = (BigInteger) getSession().createSQLQuery(sql2).setInteger("id", (Integer) ob[1])
					.uniqueResult();
			if (count != null) {
				json.setValue(count.intValue());
			} else {
				json.setValue(0);
			}
			jsonlist.add(json);
		}
		return jsonlist;
	}

	@Override
	public String loginValidate(Integer id, String password) {
		Administrator administrator = get(id);
		if (administrator != null) {
			if (administrator.getAdPassword().equals(password)) {
				return "success";
			} else {
				return "false";
			}
		} else {
			return "noexist";
		}
	}

	@Override
	public List<Company> pageQuery(Integer limit, Integer offset, String search) {
		List<Company> companylist = null;
		System.out.println("Company:pageQuery");
		if (search == null) {
			String hql = "from Company";
			companylist = getSession().createQuery(hql).setFirstResult(offset).setMaxResults(limit).list();
		} else {
			String hql = "from Company c where c.cpId like :search";
			companylist = getSession().createQuery(hql).setString("search", "%" + search + "%").setFirstResult(offset)
					.setMaxResults(limit).list();
		}
		return companylist;
	}

	@Override
	public Long getCount() {
		String hql = "select count(*) from Company";
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

}
