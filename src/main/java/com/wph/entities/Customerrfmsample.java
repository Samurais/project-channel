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
 * Customerrfmsample entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customerrfmsample", catalog = "access")

public class Customerrfmsample implements java.io.Serializable {

	// Fields

	private Integer id;
	private Customerservice customerservice;
	private Customer customer;
	private Integer level;
	private Timestamp recordtime;

	// Constructors

	/** default constructor */
	public Customerrfmsample() {
	}

	/** full constructor */
	public Customerrfmsample(Customerservice customerservice, Customer customer, Integer level, Timestamp recordtime) {
		this.customerservice = customerservice;
		this.customer = customer;
		this.level = level;
		this.recordtime = recordtime;
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
	@JoinColumn(name = "customer_id")

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "level")

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "recordtime", length = 19)

	public Timestamp getRecordtime() {
		return this.recordtime;
	}

	public void setRecordtime(Timestamp recordtime) {
		this.recordtime = recordtime;
	}

}