package com.wph.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.model.CustomerserviceInfo;
import com.wph.service.CustomerserviceInfoService;

@Service("customerserviceInfoService")
public class CustomerserviceInfoServiceImpl extends BaseServiceImpl<CustomerserviceInfo>
		implements CustomerserviceInfoService {

	@Override
	public CustomerserviceInfo getCustomerserviceInfo(Integer customerserviceid) {
		CustomerserviceInfo customerserviceInfo = new CustomerserviceInfo();
		List<Object[]> customerservicelist = null;
		Object[] customerservice = null;
		String sql = "select * from customerservice c where c.cs_id = :customerserviceid";
		customerservicelist = getSession().createSQLQuery(sql).setInteger("customerserviceid", customerserviceid)
				.list();
		if (customerservicelist.size() == 0) {
			return null;
		}
		customerservice = customerservicelist.get(0);
		if (customerservice[0] != null) {
			customerserviceInfo.setId((Integer) customerservice[0]);
		}
		if (customerservice[2] != null) {
			customerserviceInfo.setUsername((String) customerservice[2]);
		}
		if (customerservice[3] != null) {
			customerserviceInfo.setName((String) customerservice[3]);
		}
		if (customerservice[4] != null) {
			customerserviceInfo.setPhone((String) customerservice[4]);
		}
		if (customerservice[5] != null) {
			customerserviceInfo.setSex(((Character) customerservice[5]).toString());
		}
		if (customerservice[6] != null) {
			customerserviceInfo.setRegisttime(((Timestamp) customerservice[6]).toString());
		}
		if (customerservice[7] != null) {
			customerserviceInfo.setCompanyid((Integer)customerservice[7]);
		}
		if (customerservice[8] != null) {
			customerserviceInfo.setWindow((Integer) customerservice[8]);
		}
		if (customerservice[9] != null) {
			customerserviceInfo.setStatus((String) customerservice[9]);
		}
		return customerserviceInfo;
	}

}
