package com.wph.model;

import java.sql.Timestamp;

public class ShortMsg {
	private Integer customer_id;
	private Integer customerservice_id;
	private String content;
	private Integer msgtype_id;
	private Timestamp time;
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getCustomerservice_id() {
		return customerservice_id;
	}
	public void setCustomerservice_id(Integer customerservice_id) {
		this.customerservice_id = customerservice_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getMsgtype_id() {
		return msgtype_id;
	}
	public void setMsgtype_id(Integer msgtype_id) {
		this.msgtype_id = msgtype_id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
