package com.wph.service.impl;

import org.springframework.stereotype.Service;

import com.wph.entities.Customer;
import com.wph.entities.Customeronline;
import com.wph.entities.Terminal;
import com.wph.service.CustomeronlineService;

@Service("customeronlineService")
public class CustomeronlineServiceImpl extends BaseServiceImpl<Customeronline> implements CustomeronlineService {

	@Override
	public Customeronline saveCustomeronline(Integer id, Terminal terminal) {
		Customeronline customeronline = get(id);
		if (customeronline == null) {
			System.out.println("Customeronline:saveCustomeronline");
			Customeronline newcustomeronline = new Customeronline();
			newcustomeronline.setCustomer((Customer) getSession().get(Customer.class, id));
			newcustomeronline.setCustomerId(id);
			newcustomeronline.setTerminal(terminal);
			save(newcustomeronline);
		} else {
			customeronline.setTerminal(terminal);
			update(customeronline);
		}
		return customeronline;
	}

	@Override
	public void customerlogout(Integer customerid) {
		Customeronline customeronline = get(customerid);
		if (customeronline != null) {
			delete(customeronline);
		}
	}

	@Override
	public Integer getTerminal(Integer customerid) {
		Customeronline customeronline = get(customerid);
		if (customeronline == null) {
			return null;
		} else {

			return customeronline.getTerminal().getId();
		}
	}

}
