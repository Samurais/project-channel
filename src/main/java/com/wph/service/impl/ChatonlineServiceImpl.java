package com.wph.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.entities.Chatonline;
import com.wph.entities.ChatonlineId;
import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.entities.Msg;
import com.wph.service.ChatonlineService;

@Service("chatonlineService")
public class ChatonlineServiceImpl extends BaseServiceImpl<Chatonline> implements ChatonlineService {

	@Override
	public void pushChatonline(Msg msg) {
		Customer customer = msg.getCustomer();
		Customerservice customerservice = msg.getCustomerservice();

		Chatonline chatonline = new Chatonline();
		ChatonlineId chatonlineId = new ChatonlineId();
		chatonlineId.setCustomerserviceId(customerservice.getCsId());
		chatonlineId.setCustomerId(customer.getCtId());
		chatonline.setId(chatonlineId);
		chatonline.setCustomer(customer);
		chatonline.setCustomerservice(customerservice);
		chatonline.setBegintime(new Timestamp(System.currentTimeMillis()));
		getSession().save(chatonline);
	}

	@Override
	public void popChatonline(ChatonlineId chatonlineId) {
		Object chat = getSession().get(Chatonline.class, chatonlineId);
		if (chat == null) {
			return;
		}
		Chatonline chatonline = (Chatonline) getSession().get(Chatonline.class, chatonlineId);
		getSession().delete(chatonline);
	}

	@Override
	public List<Integer> customerlogoutGetService(Integer id) {
		String sql1 = "select c.customerservice_id from chatonline c where c.customer_id = :id";
		List<Integer> customerserviceIdList = getSession().createSQLQuery(sql1).setInteger("id", id).list();
		if (customerserviceIdList.size() == 0) {
			return null;
		}
		return customerserviceIdList;
	}

	@Override
	public void customerlogoutDeleteService(Integer id) {
		String sql2 = "delete from chatonline where customer_id = :id";
		getSession().createSQLQuery(sql2).setInteger("id", id).executeUpdate();
		return;	
	}

	@Override
	public List<Integer> servicelogoutGetCustomer(Integer serviceid) {
		String sql1 = "select c.customer_id from chatonline c where c.customerservice_id = :serviceid";
		List<Integer> customerIdList = getSession().createSQLQuery(sql1).setInteger("serviceid", serviceid).list();
		if (customerIdList.size() == 0) {
			return null;
		}
		return customerIdList;
	}

	@Override
	public void servicelogoutDeleteCustomer(Integer serviceid) {
		String sql2 = "delete from chatonline where customerservice_id = :serviceid";
		getSession().createSQLQuery(sql2).setInteger("serviceid", serviceid).executeUpdate();
		return;	
		
	}

}
