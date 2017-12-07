package com.wph.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.entities.Company;
import com.wph.entities.Right;
import com.wph.entities.json.RightJSON;
import com.wph.service.RightService;

@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements RightService {

	@Override
	public void saveJSON(RightJSON json) {
		Right right = new Right();
		Integer id = json.getId();
		if (id != null) {
			right.setId(id);
		} else {
			String sql = "select max(r.id) from right r";
			Integer newid = (Integer) getSession().createSQLQuery(sql).uniqueResult();
			if (newid != null) {
				right.setId(newid);
			} else {
				right.setId(0);
			}
		}

		String type = json.getType();
		right.setType(json.getType());
		if (type != null) {
			right.setType(type);
		}

		Integer companyid = json.getCompanyid();
		if (companyid != null) {
			Company company = (Company) getSession().get(Company.class, companyid);
			if (company != null) {
				right.setCompany(company);
			}
		}
		save(right);

	}

	@Override
	public BigInteger getCount(Integer companyid) {
		// 这里有兼容问题
		String sql = "select count(*) from access.right r where r.companyid=:companyid";
		return (BigInteger) getSession().createSQLQuery(sql).setInteger("companyid", companyid).uniqueResult();
	}

	@Override
	public List<RightJSON> pageQuery(Integer limit, Integer offset, String search, Integer companyid) {
		List<Object[]> rightlist = null;
		List<RightJSON> jsonlist = new ArrayList<RightJSON>();
		// 这里也有兼容的问题
		if (search == null) {
			String sql = "select * from access.right r where r.companyid = :companyid";
			rightlist = getSession().createSQLQuery(sql).setInteger("companyid", companyid).setFirstResult(offset)
					.setMaxResults(limit).list();
		} else {
			String sql = "select * from access.right r where r.companyid = :companyid and r.type like:search";
			rightlist = getSession().createQuery(sql).setInteger("companyid", companyid)
					.setString("search", "%" + search + "%").setFirstResult(offset).setMaxResults(limit).list();
		}

		if (rightlist.size() == 0) {
			return null;
		}

		for (Object[] ob : rightlist) {
			RightJSON rightJSON = new RightJSON();
			rightJSON.setId((Integer) ob[0]);
			rightJSON.setType((String) ob[1]);
			jsonlist.add(rightJSON);
		}
		return jsonlist;
	}

	@Override
	public void editUpdate(RightJSON json) {
		Right right = null;
		right = get(json.getId());
		right.setType(json.getType());
		update(right);
		return;
	}

	@Override
	public void deleteByIds(String ids) {
		// 兼容问题
		String hql = "delete from Right r where r.id in (" + ids + ")";

		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public boolean idValid(Integer id) {
		String hql = "select count(*) from Right c where c.id =" + id;
		Long count = (Long) getSession().createQuery(hql).uniqueResult();
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

}
