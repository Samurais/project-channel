package com.wph.entities.json;

import java.sql.Timestamp;

import com.wph.entities.Company;
import com.wph.entities.Product;

public class OrderJSON {
	private Integer id;
	private Integer company_id;
	private Integer product_id;
	private String time;
	private Integer customerserviceId;
	private Integer customerId;
	private String demandtime;
	private String demandaddr;
	private Integer demandphone;
	private String demandotherrequest;
	private String status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getCustomerserviceId() {
		return customerserviceId;
	}
	public void setCustomerserviceId(Integer customerserviceId) {
		this.customerserviceId = customerserviceId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getDemandtime() {
		return demandtime;
	}
	public void setDemandtime(String demandtime) {
		this.demandtime = demandtime;
	}
	public String getDemandaddr() {
		return demandaddr;
	}
	public void setDemandaddr(String demandaddr) {
		this.demandaddr = demandaddr;
	}
	public Integer getDemandphone() {
		return demandphone;
	}
	public void setDemandphone(Integer demandphone) {
		this.demandphone = demandphone;
	}
	public String getDemandotherrequest() {
		return demandotherrequest;
	}
	public void setDemandotherrequest(String demandotherrequest) {
		this.demandotherrequest = demandotherrequest;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
