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
 * Waitonline entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "waitonline", catalog = "access")

public class Waitonline implements java.io.Serializable {

	// Fields

	private Integer customerId;
	private Customer customer;
	private Timestamp begintime;
	private Timestamp realtime;

	// Constructors

	/** default constructor */
	public Waitonline() {
	}

	/** minimal constructor */
	public Waitonline(Customer customer) {
		this.customer = customer;
	}

	/** full constructor */
	public Waitonline(Customer customer, Timestamp begintime, Timestamp realtime) {
		this.customer = customer;
		this.begintime = begintime;
		this.realtime = realtime;
	}

	// Property accessors
	@Id

	@Column(name = "customer_id", unique = true, nullable = false)

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", unique = true, nullable = false, insertable = false, updatable = false)

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

	@Column(name = "realtime", length = 19)

	public Timestamp getRealtime() {
		return this.realtime;
	}

	public void setRealtime(Timestamp realtime) {
		this.realtime = realtime;
	}

}