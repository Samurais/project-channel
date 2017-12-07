package com.wph.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.entities.Userrole;
import com.wph.entities.json.RoleSelectJSON;
import com.wph.entities.json.UserroleJSON;
import com.wph.service.UserroleService;

@Service("userroleService")
public class UserroleServiceImpl extends BaseServiceImpl<Userrole> implements UserroleService {

	@Override
	public List<UserroleJSON> getServiceRole(Integer companyid) {
		List<UserroleJSON> jsonlist = new ArrayList<UserroleJSON>();
		String sql = "select c.cs_id,c.cs_username,c.cs_registtime,r.type from customerservice c,role r,userrole u where c.cs_id = u.userid and r.id = u.roleid and c.company_id =:companyid";
		List<Object[]> oblist = getSession().createSQLQuery(sql).setInteger("companyid", companyid).list();
		if (oblist.size() == 0) {
			return null;
		}
		for (Object[] ob : oblist) {
			UserroleJSON json = new UserroleJSON();
			Object id = ob[0];
			Object username = ob[1];
			Object registtime = ob[2];
			Object type = ob[3];

			if (id != null) {
				json.setId((Integer) id);
			}
			if (username != null) {
				json.setUsername((String) username);
			}
			if (registtime != null) {
				json.setRegisttime(registtime.toString());
			}
			if (type != null) {
				json.setRole((String) type);
			}
			jsonlist.add(json);
		}
		return jsonlist;
	}

	@Override
	public BigInteger getCount(Integer companyid) {
		String sql = "select count(*) from userrole u where u.userid in (select c.cs_id from customerservice c where c.company_id = :companyid)";
		return (BigInteger) getSession().createSQLQuery(sql).setInteger("companyid", companyid).uniqueResult();
	}

	@Override
	public List<UserroleJSON> pageQuery(Integer limit, Integer offset, String search, Integer companyid) {
		List<UserroleJSON> jsonlist = new ArrayList<UserroleJSON>();
		List<Object[]> oblist = null;
		if (search == null) {
			String sql = "select c.cs_id,c.cs_username,c.cs_registtime,r.type from customerservice c,role r,userrole u where c.cs_id = u.userid and r.id = u.roleid and c.company_id =:companyid";
			oblist = getSession().createSQLQuery(sql).setInteger("companyid", companyid).setFirstResult(offset)
					.setMaxResults(limit).list();
		} else {
			String sql = "select c.cs_id,c.cs_username,c.cs_registtime,r.type from customerservice c,role r,userrole u where c.cs_id = u.userid and r.id = u.roleid and c.company_id =:companyid and r.type like :search";
			oblist = getSession().createSQLQuery(sql).setInteger("companyid", companyid)
					.setString("search", "%" + search + "%").setFirstResult(offset).setMaxResults(limit).list();
		}
		if (oblist.size() == 0) {
			return null;
		}
		for (Object[] ob : oblist) {
			UserroleJSON json = new UserroleJSON();
			Object id = ob[0];
			Object username = ob[1];
			Object registtime = ob[2];
			Object type = ob[3];

			if (id != null) {
				json.setId((Integer) id);
			}
			if (username != null) {
				json.setUsername((String) username);
			}
			if (registtime != null) {
				json.setRegisttime(registtime.toString());
			}
			if (type != null) {
				json.setRole((String) type);
			}
			jsonlist.add(json);
		}
		return jsonlist;
	}

	@Override
	public List<RoleSelectJSON> getRoleSelectJSON(Integer companyid) {
		String sql = "select r.id,r.type from access.role r where r.companyid=:companyid";
		List<RoleSelectJSON> jsonlist = new ArrayList<RoleSelectJSON>();
		List<Object[]> oblist = getSession().createSQLQuery(sql).setInteger("companyid", companyid).list();
		if (oblist.size() == 0) {
			return null;
		}
		for (Object[] ob : oblist) {
			RoleSelectJSON json = new RoleSelectJSON();
			Object id = ob[0];
			Object type = ob[1];

			if (id != null) {
				json.setValue((Integer) id);
			}
			if (type != null) {
				json.setText((String) type);
			}
			jsonlist.add(json);
		}
		return jsonlist;
	}
}
