package com.wph.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Service;

import com.wph.entities.Company;
import com.wph.entities.Customerservice;
import com.wph.service.CustomerserviceService;

@Service("customerserviceService")
public class CustomerserviceServiceImpl extends BaseServiceImpl<Customerservice> implements CustomerserviceService {
	/**
	 * 基本CRUD方法在BaseDaoImpl实现
	 */

	@Override
	public Long getCount() {
		String hql = "select count(*) from Customerservice";
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	@Override
	public List<Customerservice> pageQuery(Integer limit, Integer offset) {
		String hql = "from Customerservice";
		return getSession().createQuery(hql).setFirstResult(offset).setMaxResults(limit).list();
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Customerservice c where c.csId in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();

	}

	@Override
	public List<Customerservice> pageQuery(Integer limit, Integer offset, String search) {
		List<Customerservice> servicelist = null;
		if (search == null) {
			String hql = "from Customerservice c";
			servicelist = getSession().createQuery(hql).setFirstResult(offset).setMaxResults(limit).list();
		} else {

			String hql = "from Customerservice c where c.csUsername like :search";
			servicelist = getSession().createQuery(hql).setString("search", "%" + search + "%").setFirstResult(offset)
					.setMaxResults(limit).list();
		}
		return servicelist;
	}

	@Override
	public boolean idValid(Integer id) {
		String hql = "select count(*) from Customerservice c where c.csId =" + id;
		Long count = (Long) getSession().createQuery(hql).uniqueResult();
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String loginValidate(Integer id, String password) {
		Customerservice customerservice = get(id);
		if (customerservice != null) {
			if (customerservice.getCsPassword().equals(password)) {
				return "success";
			} else {
				return "false";
			}
		} else {
			return "noexist";
		}
	}

	@Override
	public void save(Customerservice model, Integer cpId) {
		Company company = (Company) getSession().get(Company.class, cpId);
		model.setCompany(company);
		save(model);
	}

	@Override
	public List<Customerservice> pageQuery(Integer limit, Integer offset, String search, Integer cpId) {
		List<Customerservice> servicelist = null;
		System.out.println("Customerservice:pageQuery");
		if (search == null) {
			String hql = "from Customerservice c left join fetch c.customerservicerole where c.company.cpId = :cpId";
			try {
				servicelist = getSession().createQuery(hql).setInteger("cpId", cpId).setFirstResult(offset)
						.setMaxResults(limit).list();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String hql = "from Customerservice c left join fetch c.customerservicerole where c.company.cpId = :cpId and c.csUsername like :search";
			servicelist = getSession().createQuery(hql).setInteger("cpId", cpId).setString("search", "%" + search + "%")
					.setFirstResult(offset).setMaxResults(limit).list();
		}
		return servicelist;
	}

	@Override
	public BigInteger getCount(Integer cpId) {
		System.out.println("Customerservice:getCount");
		String sql = "select count(*) from Customerservice c where c.company_id = :cpId";
		return (BigInteger) getSession().createSQLQuery(sql).setInteger("cpId", cpId).uniqueResult();
	}

	@Override
	public void update(Customerservice model, Integer companyid) {
		Company company = (Company) getSession().get(Company.class, companyid);
		model.setCompany(company);
		update(model);
	}

	@Override
	public List<Integer> getCustomerserviceIdList(Integer companyid) {
		String sql = "select c.cs_id from customerservice c where c.company_id = :companyid";
		List<Integer> ids = getSession().createSQLQuery(sql).addScalar("cs_id", StandardBasicTypes.INTEGER)
				.setInteger("companyid", companyid).list();
		System.out.println("客服ID编号:" + ids.toString());
		return ids;
	}

	@Override
	public String idListtoString(List<Integer> ids) {
		StringBuffer str = new StringBuffer();
		for (Integer i = 0; i < ids.size(); i++) {
			System.out.println(ids.get(i));
			str.append(ids.get(i) + ",");
		}
		;
		String string = str.substring(0, str.lastIndexOf(","));
		System.out.println(string);
		return string;
	}

	@Override
	public List<Integer> getCustomerserviceIdList(Integer companyid, Integer limit, Integer offset) {
		String sql = "select c.cs_id from customerservice c where c.company_id = :companyid";
		List<Integer> ids = getSession().createSQLQuery(sql).addScalar("cs_id", StandardBasicTypes.INTEGER)
				.setInteger("companyid", companyid).setFirstResult(offset).setMaxResults(limit).list();
		return ids;
	}

	@Override
	public Boolean isOnChat(Integer serviceid) {
		// 查看是否正在会话
		String sql1 = "select count(*) from chatonline c where c.customerservice_id = :serviceid";
		BigInteger chatonlinecount = (BigInteger) getSession().createSQLQuery(sql1).setInteger("serviceid", serviceid)
				.uniqueResult();
		return chatonlinecount.intValue() > 0;
	}

	@Override
	public Boolean isOnWait(Integer serviceid) {
		// 查看是否在排队窗口
		String sql2 = "select count(*) from waitserviceonline w where w.cs_id = :serviceid";
		BigInteger waitserviceonlinecount = (BigInteger) getSession().createSQLQuery(sql2)
				.setInteger("serviceid", serviceid).uniqueResult();
		return waitserviceonlinecount.intValue() > 0;
	}

	@Override
	public Boolean isOnline(Integer serviceid) {
		return isOnChat(serviceid) || isOnWait(serviceid);
	}

	@Override
	public void editService(Customerservice service) {
		Customerservice newservice = new Customerservice();
		Integer id = service.getCsId();
		if (id == null) {
			return;
		}
		Customerservice oldservice = get(id);
		String password = service.getCsPassword();
		String phone = service.getCsPhone();
		Timestamp timestamp = service.getCsRegisttime();
		String sex = service.getCsSex();
		String username = service.getCsUsername();
		Integer window = service.getCsWindow();

		newservice.setCsId(id);
		if (password != null) {
			oldservice.setCsPassword(password);
		} else {
			// oldservice.setCsPassword(oldservice.getCsPassword());
		}
		if (phone != null) {
			oldservice.setCsPhone(phone);
		} else {
			// oldservice.setCsPhone(oldservice.getCsPhone());
		}
		if (timestamp != null) {
			oldservice.setCsRegisttime(timestamp);
			;
		} else {
			// oldservice.setCsRegisttime(oldservice.getCsRegisttime());
		}
		if (sex != null) {
			oldservice.setCsSex(sex);
		} else {
			// oldservice.setCsSex(oldservice.getCsSex());
		}
		if (username != null) {
			oldservice.setCsUsername(username);
		} else {
			// oldservice.setCsUsername(oldservice.getCsUsername());
		}
		if (window != null) {
			oldservice.setCsWindow(window);
		} else {
			// oldservice.setCsWindow(oldservice.getCsWindow());
		}
		update(oldservice);
		return;
	}

	@Override
	public List<Customerservice> pageQueryJoinRole(Integer limit, Integer offset, String search, Integer companyid) {
		String hql = "from Customerservice c left join fetch c.customerservicerole";
		return getSession().createQuery(hql).setFirstResult(offset).setMaxResults(limit).list();
	}

}
