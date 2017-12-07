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
 * Customerserviceonline entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customerserviceonline", catalog = "access")

public class Customerserviceonline implements java.io.Serializable {

	// Fields

	private Integer customerserviceUserid;
	private Customerservice customerservice;
	private Terminal terminal;

	// Constructors

	/** default constructor */
	public Customerserviceonline() {
	}

	/** minimal constructor */
	public Customerserviceonline(Customerservice customerservice) {
		this.customerservice = customerservice;
	}

	/** full constructor */
	public Customerserviceonline(Customerservice customerservice, Terminal terminal) {
		this.customerservice = customerservice;
		this.terminal = terminal;
	}

	// Property accessors
	@Id

	@Column(name = "customerservice_userid", unique = true, nullable = false)

	public Integer getCustomerserviceUserid() {
		return this.customerserviceUserid;
	}

	public void setCustomerserviceUserid(Integer customerserviceUserid) {
		this.customerserviceUserid = customerserviceUserid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerservice_userid", unique = true, nullable = false, insertable = false, updatable = false)

	public Customerservice getCustomerservice() {
		return this.customerservice;
	}

	public void setCustomerservice(Customerservice customerservice) {
		this.customerservice = customerservice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "terminal_id")

	public Terminal getTerminal() {
		return this.terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

}