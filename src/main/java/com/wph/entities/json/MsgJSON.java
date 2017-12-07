package com.wph.entities.json;

import java.util.ArrayList;
import java.util.List;

public class MsgJSON {
	private Integer id;
	private String sendtime;
	private String content;
	private String sensitiveword;
	private Integer msgtype_id;
	private Integer customer_id;
	private Integer customerservice_id;
	private Integer company_id;
	
	
	public MsgJSON() {
		super();
	}

	public MsgJSON(Integer id, String sendtime, String content, String sensitiveword, Integer msgtype_id,
			Integer customer_id, Integer customerservice_id, Integer company_id) {
		super();
		this.id = id;
		this.sendtime = sendtime;
		this.content = content;
		this.sensitiveword = sensitiveword;
		this.msgtype_id = msgtype_id;
		this.customer_id = customer_id;
		this.customerservice_id = customerservice_id;
		this.company_id = company_id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSensitiveword() {
		return sensitiveword;
	}
	public void setSensitiveword(String sensitiveword) {
		this.sensitiveword = sensitiveword;
	}
	public Integer getMsgtype_id() {
		return msgtype_id;
	}
	public void setMsgtype_id(Integer msgtype_id) {
		this.msgtype_id = msgtype_id;
	}
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
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	
	
	
}
