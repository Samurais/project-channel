package com.wph.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.entities.Company;
import com.wph.entities.Role;
import com.wph.entities.json.RoleJSON;
import com.wph.entities.json.RoleRightJSON;
import com.wph.service.RoleService;

@Service("roleService")
public class RoleServiceImple extends BaseServiceImpl<Role> implements RoleService {

	@Override
	public BigInteger getCount(Integer companyid) {
		// 这里有兼容问题
		String sql = "select count(*) from access.role r where r.companyid=:companyid";
		return (BigInteger) getSession().createSQLQuery(sql).setInteger("companyid", companyid).uniqueResult();
	}

	@Override
	public List<RoleJSON> pageQuery(Integer limit, Integer offset, String search, Integer companyid) {
		List<Object[]> rolelist = null;
		List<RoleJSON> jsonlist = new ArrayList<RoleJSON>();
		// 这里也有兼容的问题
		if (search == null) {
			String sql = "select * from access.role r where r.companyid = :companyid";
			rolelist = getSession().createSQLQuery(sql).setInteger("companyid", companyid).setFirstResult(offset)
					.setMaxResults(limit).list();
		} else {
			String sql = "select * from access.role r where r.companyid = :companyid and r.type like:search";
			rolelist = getSession().createQuery(sql).setInteger("companyid", companyid)
					.setString("search", "%" + search + "%").setFirstResult(offset).setMaxResults(limit).list();
		}

		if (rolelist.size() == 0) {
			return null;
		}

		for (Object[] ob : rolelist) {
			RoleJSON roleJSON = new RoleJSON();
			roleJSON.setId((Integer) ob[0]);
			roleJSON.setType((String) ob[1]);
			jsonlist.add(roleJSON);
		}
		return jsonlist;
	}

	@Override
	public void editUpdate(RoleJSON json) {
		Role role = null;
		role = get(json.getId());
		role.setType(json.getType());
		update(role);
		return;
	}

	@Override
	public void deleteByIds(String ids) {
		// 兼容问题
		String hql = "delete from Roler where r.id in (" + ids + ")";

		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public void saveJSON(RoleJSON json) {
		Role role = new Role();
		Integer id = json.getId();
		if (id != null) {
			role.setId(id);
		} else {
			String sql = "select max(r.id) from role r";
			Integer newid = (Integer) getSession().createSQLQuery(sql).uniqueResult();
			if (newid != null) {
				role.setId(newid);
			} else {
				role.setId(0);
			}
		}

		String type = json.getType();
		role.setType(json.getType());
		if (type != null) {
			role.setType(type);
		}

		Integer companyid = json.getCompanyid();
		if (companyid != null) {
			Company company = (Company) getSession().get(Company.class, companyid);
			if (company != null) {
				role.setCompany(company);
			}
		}
		save(role);

	}

	@Override
	public boolean idValid(Integer id) {
		String hql = "select count(*) from Role c where c.id =" + id;
		Long count = (Long) getSession().createQuery(hql).uniqueResult();
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<RoleRightJSON> getRoleRight(Integer roleid, Integer companyid) {
		List<RoleRightJSON> jsonlist = new ArrayList<RoleRightJSON>();
		String sql1 = "select r.id,r.type from access.right r where r.companyid = :companyid";
		List<Object[]> rightlist = getSession().createSQLQuery(sql1).setInteger("companyid", companyid).list();
		if (rightlist.size() == 0) {
			return null;
		}
		String sql2 = "select r.rightid from roleright r where r.roleid = :roleid";
		List<Integer> rolerightlist = getSession().createSQLQuery(sql2).setInteger("roleid", roleid).list();
		for (Object[] ob : rightlist) {
			RoleRightJSON json = new RoleRightJSON();
			json.setId((Integer) ob[0]);
			json.setType((String) ob[1]);
			json.setIscheck(false);
			for (Integer rr : rolerightlist) {
				if (rr.equals((Integer) ob[0])) {
					json.setIscheck(true);
				}
			}
			jsonlist.add(json);
		}
		return jsonlist;
	}

	@Override
	public List<RoleJSON> pageQueryJoinRight(Integer limit, Integer offset, String search, Integer companyid) {
		List<Object[]> rolelist = null;
		List<RoleJSON> jsonlist = new ArrayList<RoleJSON>();
		// 这里也有兼容的问题
		if (search == null) {
			String sql = "select * from access.role r where r.companyid = :companyid";
			rolelist = getSession().createSQLQuery(sql).setInteger("companyid", companyid).setFirstResult(offset)
					.setMaxResults(limit).list();
		} else {
			String sql = "select * from access.role r where r.companyid = :companyid and r.type like:search";
			rolelist = getSession().createQuery(sql).setInteger("companyid", companyid)
					.setString("search", "%" + search + "%").setFirstResult(offset).setMaxResults(limit).list();
		}

		if (rolelist.size() == 0) {
			return null;
		}

		for (Object[] ob : rolelist) {
			RoleJSON roleJSON = new RoleJSON();
			roleJSON.setId((Integer) ob[0]);
			roleJSON.setType((String) ob[1]);
			String sql = "select r.type from access.right r ,access.roleright rr where r.id = rr.rightid and rr.roleid=:id";
			List<String> rights = getSession().createSQLQuery(sql).setInteger("id", (Integer) ob[0]).list();
			if(rights.size()>0){
				roleJSON.setRight(rights.toString());
			}
			jsonlist.add(roleJSON);
		}
		return jsonlist;
	}

}
