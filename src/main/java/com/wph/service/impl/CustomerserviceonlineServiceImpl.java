package com.wph.service.impl;

import org.springframework.stereotype.Service;

import com.wph.entities.Customerservice;
import com.wph.entities.Customerserviceonline;
import com.wph.entities.Terminal;
import com.wph.service.CustomerserviceonlineService;

@Service("customerserviceonlineService")
public class CustomerserviceonlineServiceImpl extends BaseServiceImpl<Customerserviceonline>
		implements CustomerserviceonlineService {

	@Override
	public Customerserviceonline saveCustomerserviceonline(Integer id, Terminal terminal) {
		Customerserviceonline customerserviceonline = get(id);
		if (customerserviceonline == null) {
			customerserviceonline = new Customerserviceonline();
			customerserviceonline.setCustomerservice((Customerservice) getSession().get(Customerservice.class, id));
			customerserviceonline.setCustomerserviceUserid(id);
			customerserviceonline.setTerminal(terminal);
			save(customerserviceonline);
		} else {
			customerserviceonline.setTerminal(terminal);
			update(customerserviceonline);
		}
		return customerserviceonline;
	}

	@Override
	public void servicelogout(Integer serviceid) {
		Customerserviceonline customerserviceonline = get(serviceid);
		if (customerserviceonline != null) {
			delete(customerserviceonline);
		}

	}

	@Override
	public Integer getTerminal(Integer serviceid) {
		Customerserviceonline on = get(serviceid);
		if (on == null) {
			return null;
		} else {
			return on.getTerminal().getId();
		}
	}

}
