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
 * Customeronline entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customeronline", catalog = "access")

public class Customeronline implements java.io.Serializable {

	// Fields

	private Integer customerId;
	private Terminal terminal;
	private Customer customer;

	// Constructors

	/** default constructor */
	public Customeronline() {
	}

	/** minimal constructor */
	public Customeronline(Customer customer) {
		this.customer = customer;
	}

	/** full constructor */
	public Customeronline(Terminal terminal, Customer customer) {
		this.terminal = terminal;
		this.customer = customer;
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
	@JoinColumn(name = "terminal_id")

	public Terminal getTerminal() {
		return this.terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", unique = true, nullable = false, insertable = false, updatable = false)

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}