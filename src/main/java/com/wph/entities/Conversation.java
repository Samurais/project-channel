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
 * Conversation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conversation", catalog = "access")

public class Conversation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Customerservice customerservice;
	private Customer customer;
	private Timestamp begintime;
	private Timestamp endtime;
	private String keyword;
	private String degree;
	private String firstkeyword;

	// Constructors

	/** default constructor */
	public Conversation() {
	}

	/** minimal constructor */
	public Conversation(Customer customer) {
		this.customer = customer;
	}

	/** full constructor */
	public Conversation(Customerservice customerservice, Customer customer, Timestamp begintime, Timestamp endtime,
			String keyword, String degree, String firstkeyword) {
		this.customerservice = customerservice;
		this.customer = customer;
		this.begintime = begintime;
		this.endtime = endtime;
		this.keyword = keyword;
		this.degree = degree;
		this.firstkeyword = firstkeyword;
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
	@JoinColumn(name = "costomerService_id")

	public Customerservice getCustomerservice() {
		return this.customerservice;
	}

	public void setCustomerservice(Customerservice customerservice) {
		this.customerservice = customerservice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "begintime", length = 19)

	public Timestamp getBegintime() {
		return this.begintime;
	}

	public void setBegintime(Timestamp begintime) {
		this.begintime = begintime;
	}

	@Column(name = "endtime", length = 19)

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	@Column(name = "keyword")

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name = "degree", length = 45)

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name = "firstkeyword")

	public String getFirstkeyword() {
		return this.firstkeyword;
	}

	public void setFirstkeyword(String firstkeyword) {
		this.firstkeyword = firstkeyword;
	}

}