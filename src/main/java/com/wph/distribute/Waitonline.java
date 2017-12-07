package com.wph.distribute;

import java.sql.Timestamp;

public class Waitonline {
	private Integer customerId;
	private Customer customer;
	private Timestamp begintime;
	private Timestamp realtime;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Timestamp getBegintime() {
		return begintime;
	}
	public void setBegintime(Timestamp begintime) {
		this.begintime = begintime;
	}
	public Timestamp getRealtime() {
		return realtime;
	}
	public void setRealtime(Timestamp realtime) {
		this.realtime = realtime;
	}
	
	

}
