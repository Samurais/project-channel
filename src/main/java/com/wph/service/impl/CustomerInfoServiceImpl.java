package com.wph.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.model.CustomerInfo;
import com.wph.service.CustomerInfoService;

@Service("customerInfoService")
public class CustomerInfoServiceImpl extends BaseServiceImpl<CustomerInfo> implements CustomerInfoService {

	@Override
	public CustomerInfo getCustomerInfo(Integer customerid) {
		CustomerInfo customerInfo = new CustomerInfo();
		List<Object[]> customerlist = null;
		Object[] customer = null;
		String sql1 = "select * from customer c where c.ct_id = :customerid";
		customerlist = getSession().createSQLQuery(sql1).setInteger("customerid", customerid).list();
		if (customerlist.size() == 0) {
			return null;
		}
		customer = customerlist.get(0);
		if (customer[0] != null) {
			customerInfo.setId((Integer) customer[0]);
		}
		if (customer[2] != null) {
			customerInfo.setUsername((String) customer[2]);
		}
		if (customer[3] != null) {
			customerInfo.setName((String) customer[3]);
		}
		if (customer[4] != null) {
			customerInfo.setPhone((String) customer[4]);
		}
		if (customer[5] != null) {
			customerInfo.setMailbox((String) customer[5]);
		}
		if (customer[6] != null) {
			customerInfo.setSex(((Character) customer[6]).toString());
		}
		if (customer[8] != null) {
			customerInfo.setLevel((Double) customer[8]);
		}
		if (customer[9] != null) {
			customerInfo.setRegisttime(customer[9].toString());
		}

		String sql2 = "select * from customerloginrecord c where c.customer_id = :customerid and "
				+ "c.logintime=(select max(o.logintime) from customerloginrecord o where o.customer_id = :customerid)";
		customerlist = getSession().createSQLQuery(sql2).setInteger("customerid", customerid)
				.setInteger("customerid", customerid).list();
		if (customerlist.size() != 0) {
			customer = customerlist.get(0);
			if (customer[1] != null) {
				customerInfo.setIpaddr((String) customer[1]);
			}
			if (customer[2] != null) {
				customerInfo.setArea((String) customer[2]);
			}
			if (customer[3] != null) {
				customerInfo.setLogintime(((Timestamp) customer[3]).toString());
			}
			if (customer[5] != null) {
				customerInfo.setReferer((String) customer[5]);
			}
			if (customer[6] != null) {
				customerInfo.setBrowserinfo((String) customer[6]);
			}
			if (customer[7] != null) {
				customerInfo.setHostname((String) customer[7]);
			}
			if (customer[8] != null) {
				customerInfo.setSysteminfo((String) customer[8]);
			}
			if (customer[9] != null) {
				customerInfo.setMacaddr((String) customer[8]);
			}
			if (customer[10] != null) {
				customerInfo.setRegion((String) customer[10]);
			}
			if (customer[11] != null) {
				customerInfo.setReferkeyword((String) customer[11]);
			}
		}
		String sql3 = "select * from conversation c where c.customer_id = :customerid and "
				+ "c.begintime=(select max(o.begintime) from conversation o where o.customer_id = :customerid"
				+ " and o.endtime is not null)";
		customerlist = getSession().createSQLQuery(sql3).setInteger("customerid", customerid)
				.setInteger("customerid", customerid).list();
		if (customerlist.size() != 0) {
			customer = customerlist.get(0);
			if (customer[1] != null) {
				customerInfo.setLastconversationtime(((Timestamp) customer[1]).toString());
			}
			if (customer[7] != null) {
				customerInfo.setLastconversationfirstkeyword((String) customer[7]);
			}
			if (customer[3] != null) {
				customerInfo.setLastconversationkeyword((String) customer[3]);
			}
		}
		return customerInfo;
	}

}
