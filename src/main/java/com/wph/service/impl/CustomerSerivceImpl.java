package com.wph.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.entities.Company;
import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.service.CustomerService;

@Service("customerService")
public class CustomerSerivceImpl extends BaseServiceImpl<Customer> implements CustomerService {

	@Override
	public Long getCount() {
		String hql = "select count(*) from Customer";
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	@Override
	public List<Customer> pageQuery(Integer limit, Integer offset) {
		String hql = "from Customer";
		return getSession().createQuery(hql).setFirstResult(offset).setMaxResults(limit).list();
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Customer c where c.ctId in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();

	}

	@Override
	public List<Customer> pageQuery(Integer limit, Integer offset, String search) {
		List<Customer> customerlist = null;
		if (search == null) {
			String hql = "from Customer c";
			customerlist = getSession().createQuery(hql).setFirstResult(offset).setMaxResults(limit).list();
		} else {

			String hql = "from Customer c where c.ctUsername like :search";
			customerlist = getSession().createQuery(hql).setString("search", "%" + search + "%").setFirstResult(offset)
					.setMaxResults(limit).list();
		}
		return customerlist;
	}

	@Override
	public boolean idValid(Integer id) {
		String hql = "select count(*) from Customer c where c.ctId =" + id;
		Long count = (Long) getSession().createQuery(hql).uniqueResult();
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String loginValidate(Integer id, String password) {
		Customer customer = get(id);
		if (customer != null) {
			if (customer.getCtPassword().equals(password)) {
				return "success";
			} else {
				return "false";
			}
		} else {
			return "noexist";
		}
	}

	@Override
	public String loginValidate(Integer loginid, String loginpassword, Integer companyid) {
		Customer customer = get(loginid);
		String password = customer.getCtPassword();
		Company company = customer.getCompany();
		if (customer != null) {
			if (company != null) {
				if (!company.getCpId().equals(companyid)) {
					return "false";
				} else {
					if (password.equals(loginpassword)) {
						return "success";
					} else {
						return "false";
					}
				}
			} else {
				if (password.equals(loginpassword)) {
					return "success";
				} else {
					return "false";
				}
			}
		} else {
			return "noexist";
		}
	}

	@Override
	public void save(Customer model, Integer cpId) {
		Company company = (Company) getSession().get(Company.class, cpId);
		model.setCompany(company);
		model.setCtLevel((double) 0);
		model.setCtRegisttime((new Timestamp(System.currentTimeMillis())));
		try {
			save(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Customer> pageQuery(Integer limit, Integer offset, String search, Integer cpId) {
		List<Customer> customerlist = null;
		System.out.println("Customer:pageQuery");
		if (search == null) {
			System.out.println(cpId);
			String sql = "select * from customer c where c.company_id = :cpId";
			customerlist = getSession().createSQLQuery(sql).addEntity(Customer.class).setInteger("cpId", cpId)
					.setFirstResult(offset).setMaxResults(limit).list();
		} else {
			String sql = "select * from customer c where c.company_id = :cpId and c.ct_username like :search";
			customerlist = getSession().createSQLQuery(sql).addEntity(Customer.class).setInteger("cpId", cpId)
					.setString("search", "%" + search + "%").setFirstResult(offset).setMaxResults(limit).list();
		}
		return customerlist;
	}

	@Override
	public BigInteger getCount(Integer cpId) {
		System.out.println("Customer:getCount");
		String sql = "select count(*) from Customer c where c.company_id = :cpId";
		return (BigInteger) getSession().createSQLQuery(sql).setInteger("cpId", cpId).uniqueResult();
	}

	@Override
	public void update(Customer model, Integer companyid) {
		Company company = (Company) getSession().get(Company.class, companyid);
		model.setCompany(company);
		update(model);
	}

	@Override
	public Integer getCompanyid(Integer customerid) {
		String sql = "select c.company_id from customer c where c.ct_id = :customerid";
		Object companyid = getSession().createSQLQuery(sql).setInteger("customerid", customerid).uniqueResult();
		if (companyid == null) {
			return null;
		} else {
			return (Integer) companyid;
		}
	}

}
