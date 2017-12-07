package com.wph.service;

import com.wph.entities.Customeronline;
import com.wph.entities.Customerserviceonline;
import com.wph.entities.Terminal;

public interface CustomerserviceonlineService extends BaseService<Customerserviceonline> {
	public Customerserviceonline saveCustomerserviceonline(Integer id,Terminal terminal);

	
	public void servicelogout(Integer serviceid);


	public Integer getTerminal(Integer serviceid);
}
