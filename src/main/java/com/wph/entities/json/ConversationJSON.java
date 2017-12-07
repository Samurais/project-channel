package com.wph.entities.json;

import java.sql.Timestamp;

import com.wph.entities.Customer;
import com.wph.entities.Customerservice;

public class ConversationJSON {

	private Integer id;
	private Integer customerservice_id;
	private Integer customer_id;
	private String begintime;
	private String endtime;
	private String keyword;
	private String degree;
	private String firstkeyword;

	public ConversationJSON() {
		super();
	}

	public ConversationJSON(Integer id, Integer customerservice_id, Integer customer_id, String begintime,
			String endtime, String keyword, String degree) {
		super();
		this.id = id;
		this.customerservice_id = customerservice_id;
		this.customer_id = customer_id;
		this.begintime = begintime;
		this.endtime = endtime;
		this.keyword = keyword;
		this.degree = degree;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerservice_id() {
		return customerservice_id;
	}

	public void setCustomerservice_id(Integer customerservice_id) {
		this.customerservice_id = customerservice_id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getFirstkeyword() {
		return firstkeyword;
	}

	public void setFirstkeyword(String firstkeyword) {
		this.firstkeyword = firstkeyword;
	}

}
