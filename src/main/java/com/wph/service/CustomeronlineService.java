package com.wph.service;

import com.wph.entities.Customeronline;
import com.wph.entities.Terminal;

public interface CustomeronlineService extends BaseService<Customeronline> {
	public Customeronline saveCustomeronline(Integer id,Terminal terminal);
	
	public void customerlogout(Integer customerid);

	public Integer getTerminal(Integer customerid);
}
