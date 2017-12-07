package com.wph.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Knowledgebase entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "knowledgebase", catalog = "access")

public class Knowledgebase implements java.io.Serializable {

	// Fields

	private Integer id;
	private Customerservice customerservice;
	private Company company;
	private String title;
	private String category;
	private Timestamp createtime;
	private String content;

	// Constructors

	/** default constructor */
	public Knowledgebase() {
	}

	/** full constructor */
	public Knowledgebase(Customerservice customerservice, Company company, String title, String category,
			Timestamp createtime, String content) {
		this.customerservice = customerservice;
		this.company = company;
		this.title = title;
		this.category = category;
		this.createtime = createtime;
		this.content = content;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerservice_id")

	public Customerservice getCustomerservice() {
		return this.customerservice;
	}

	public void setCustomerservice(Customerservice customerservice) {
		this.customerservice = customerservice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "title", length = 45)

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "category", length = 45)

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "createtime", length = 19)

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "content", length = 16777215)

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}