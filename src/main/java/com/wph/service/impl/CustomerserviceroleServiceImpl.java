package com.wph.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.entities.Customerserviceright;
import com.wph.entities.Customerservicerightrole;
import com.wph.entities.CustomerservicerightroleId;
import com.wph.entities.Customerservicerole;
import com.wph.entities.json.CustomerservicerightroleJSON;
import com.wph.entities.json.CustomerserviceroleJSON;
import com.wph.entities.json.CustomerserviceroleSelectJSON;
import com.wph.service.CustomerserviceroleService;

@Service("customerserviceroleService")
public class CustomerserviceroleServiceImpl extends BaseServiceImpl<Customerservicerole>
		implements CustomerserviceroleService {

	@Override
	public Long getCount(Integer companyid) {
		String hql = "select count(*) from Customerservicerole c where c.company.cpId = :companyid";
		return (Long) getSession().createQuery(hql).setInteger("companyid", companyid).uniqueResult();
	}

	@Override
	public List<Customerservicerole> pageQuery(Integer limit, Integer offset, String search, Integer companyid) {
		if (search == null) {
			String hql = "from Customerservicerole c where c.company.cpId = :companyid";
			return getSession().createQuery(hql).setInteger("companyid", companyid).list();
		} else {
			String hql = "from Customerservicerole c where c.company.cpId = :companyid and c.type like :search";
			return getSession().createQuery(hql).setInteger("companyid", companyid)
					.setString("search", "%" + search + "%").list();
		}
	}

	@Override
	public List<CustomerserviceroleJSON> pageQueryJoinRight(Integer limit, Integer offset, String search,
			Integer companyid) {
		List<Object[]> rolelist = null;
		List<CustomerserviceroleJSON> jsonlist = new ArrayList<CustomerserviceroleJSON>();
		// 这里也有兼容的问题
		if (search == null) {
			String sql = "select * from customerservicerole r where r.companyid = :companyid";
			rolelist = getSession().createSQLQuery(sql).setInteger("companyid", companyid).setFirstResult(offset)
					.setMaxResults(limit).list();
		} else {
			String sql = "select * from customerservicerole r where r.companyid = :companyid and r.type like:search";
			rolelist = getSession().createQuery(sql).setInteger("companyid", companyid)
					.setString("search", "%" + search + "%").setFirstResult(offset).setMaxResults(limit).list();
		}
		if (rolelist.size() == 0) {
			return null;
		}
		for (Object[] ob : rolelist) {
			CustomerserviceroleJSON roleJSON = new CustomerserviceroleJSON();
			roleJSON.setId((Integer) ob[0]);
			roleJSON.setType((String) ob[1]);
			roleJSON.setLevel((Integer) ob[2]);
			String sql = "select r.type from customerserviceright r ,customerservicerightrole rr where r.id = rr.customerservicerightid and rr.customerserviceroleid =:id";
			List<String> rights = null;
			rights = getSession().createSQLQuery(sql).setInteger("id", (Integer) ob[0]).list();
			if (rights.size() > 0) {
				roleJSON.setRight(rights.toString());
			}
			jsonlist.add(roleJSON);
		}
		return jsonlist;
	}

	@Override
	public void editUpdate(Customerservicerole role) {
		update(role);
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Customerservicerole where id in(" + ids + ")";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public boolean idValid(Integer id) {
		String hql = "select count(*) from Customerservicerole c where c.id =" + id;
		Long count = (Long) getSession().createQuery(hql).uniqueResult();
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<CustomerservicerightroleJSON> getRoleRight(Integer roleid, Integer companyid) {
		List<CustomerservicerightroleJSON> jsonlist = new ArrayList<CustomerservicerightroleJSON>();
		String sql1 = "select r.id,r.type from customerserviceright r where r.companyid = :companyid";
		List<Object[]> rightlist = getSession().createSQLQuery(sql1).setInteger("companyid", companyid).list();
		if (rightlist.size() == 0) {
			return null;
		}
		String sql2 = "select r.customerservicerightid from customerservicerightrole r where r.customerserviceroleid = :roleid";
		List<Integer> rolerightlist = getSession().createSQLQuery(sql2).setInteger("roleid", roleid).list();
		for (Object[] ob : rightlist) {
			CustomerservicerightroleJSON json = new CustomerservicerightroleJSON();
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
	public void updateRightByIds(Integer id, String ids) {
		if (ids == null || ids == "") {
			return;
		}
		String hql = "select c.id from Customerserviceright c where c.id in (" + ids + ")";
		List<Integer> idlist = getSession().createQuery(hql).list();
		if (idlist.size() == 0) {
			return;
		}
		String sql = "delete from customerservicerightrole where customerserviceroleid = :id";
		try {

			getSession().createSQLQuery(sql).setInteger("id", id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Integer thisid : idlist) {
			Customerservicerightrole rightrole = new Customerservicerightrole();
			CustomerservicerightroleId rightroleid = new CustomerservicerightroleId();

			rightroleid.setCustomerservicerightid(thisid);
			rightroleid.setCustomerserviceroleid(id);

			rightrole.setId(rightroleid);
			rightrole.setCustomerserviceright(
					(Customerserviceright) getSession().get(Customerserviceright.class, thisid));
			rightrole.setCustomerservicerole((Customerservicerole) getSession().get(Customerservicerole.class, id));

			getSession().save(rightrole);
		}
		;
	}

	@Override
	public List<CustomerserviceroleSelectJSON> getRoleSelectJSON(Integer companyid) {
		List<CustomerserviceroleSelectJSON> jsonlist = new ArrayList<CustomerserviceroleSelectJSON>();
		String hql = "from Customerservicerole c where c.company.cpId = :companyid";
		List<Customerservicerole> rolelist = getSession().createQuery(hql).setInteger("companyid", companyid).list();
		for(Customerservicerole c :rolelist){
			CustomerserviceroleSelectJSON json = new CustomerserviceroleSelectJSON();
			json.setId(c.getId());
			json.setName(c.getType());
			jsonlist.add(json);
		}
		return jsonlist;
	}

}
