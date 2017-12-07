package com.wph.entities.json;

import java.sql.Timestamp;

public class KnowledgebaseJSON {
	private Integer id;
	private String title;
	private String category;
	private String createtime;
	private String content;
	private Integer company_id;
	private Integer customerservice_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public Integer getCustomerservice_id() {
		return customerservice_id;
	}
	public void setCustomerservice_id(Integer customerservice_id) {
		this.customerservice_id = customerservice_id;
	}
	
	
}
