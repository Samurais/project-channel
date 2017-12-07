package com.wph.entities;

import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Chatonline entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "chatonline", catalog = "access")

public class Chatonline implements java.io.Serializable {

	// Fields

	private ChatonlineId id;
	private Customerservice customerservice;
	private Customer customer;
	private Timestamp begintime;

	// Constructors

	/** default constructor */
	public Chatonline() {
	}

	/** minimal constructor */
	public Chatonline(ChatonlineId id, Customerservice customerservice, Customer customer) {
		this.id = id;
		this.customerservice = customerservice;
		this.customer = customer;
	}

	/** full constructor */
	public Chatonline(ChatonlineId id, Customerservice customerservice, Customer customer, Timestamp begintime) {
		this.id = id;
		this.customerservice = customerservice;
		this.customer = customer;
		this.begintime = begintime;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "customerId", column = @Column(name = "customer_id", nullable = false) ),
			@AttributeOverride(name = "customerserviceId", column = @Column(name = "customerservice_id", nullable = false) ) })

	public ChatonlineId getId() {
		return this.id;
	}

	public void setId(ChatonlineId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerservice_id", nullable = false, insertable = false, updatable = false)

	public Customerservice getCustomerservice() {
		return this.customerservice;
	}

	public void setCustomerservice(Customerservice customerservice) {
		this.customerservice = customerservice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false, insertable = false, updatable = false)

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

}