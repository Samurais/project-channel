package com.wph.service;

import com.wph.entities.Terminal;

public interface TerminalService extends BaseService<Terminal> {

	public Terminal getByStatusid(Integer typeid,Integer statusid);
	
	public Terminal saveTerminal(Integer id,Integer terminaltype,String status);
	
	public Terminal saveTerminal(Integer id,String identifi,Integer terminaltype,String status);
	
	public Integer checkBinding(String indenti);
}
