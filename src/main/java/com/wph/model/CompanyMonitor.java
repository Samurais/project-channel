package com.wph.model;

import java.util.List;

public class CompanyMonitor {
	private Integer conversation;
	private Integer degree;
	private Integer newcustomer;
	private Integer danger;
	private List<ServiceActive> serviceactivelist;
	
	
	public CompanyMonitor() {
		super();
	}
	public CompanyMonitor(Integer conversation, Integer degree, Integer newcustomer, Integer danger,
			List<ServiceActive> serviceactivelist) {
		super();
		this.conversation = conversation;
		this.degree = degree;
		this.newcustomer = newcustomer;
		this.danger = danger;
		this.serviceactivelist = serviceactivelist;
	}
	public Integer getConversation() {
		return conversation;
	}
	public void setConversation(Integer conversation) {
		this.conversation = conversation;
	}
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	public Integer getNewcustomer() {
		return newcustomer;
	}
	public void setNewcustomer(Integer newcustomer) {
		this.newcustomer = newcustomer;
	}
	public Integer getDanger() {
		return danger;
	}
	public void setDanger(Integer danger) {
		this.danger = danger;
	}
	public List<ServiceActive> getServiceactivelist() {
		return serviceactivelist;
	}
	public void setServiceactivelist(List<ServiceActive> serviceactivelist) {
		this.serviceactivelist = serviceactivelist;
	}
	
	
}
