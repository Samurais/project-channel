package com.wph.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Leavingmsg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "leavingmsg", catalog = "access")

public class Leavingmsg implements java.io.Serializable {

	// Fields

	private Integer id;
	private Company company;
	private Customerservice customerservice;
	private String titile;
	private String content;

	// Constructors

	/** default constructor */
	public Leavingmsg() {
	}

	/** full constructor */
	public Leavingmsg(Company company, Customerservice customerservice, String titile, String content) {
		this.company = company;
		this.customerservice = customerservice;
		this.titile = titile;
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
	@JoinColumn(name = "companyid")

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serviceid")

	public Customerservice getCustomerservice() {
		return this.customerservice;
	}

	public void setCustomerservice(Customerservice customerservice) {
		this.customerservice = customerservice;
	}

	@Column(name = "titile", length = 45)

	public String getTitile() {
		return this.titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	@Column(name = "content")

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}