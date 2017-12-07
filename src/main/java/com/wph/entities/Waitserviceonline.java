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
 * Waitserviceonline entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "waitserviceonline", catalog = "access")

public class Waitserviceonline implements java.io.Serializable {

	// Fields

	private Integer csId;
	private Customerservice customerservice;
	private Timestamp begintime;
	private Integer csWindowtotal;
	private Integer csWindowremain;

	// Constructors

	/** default constructor */
	public Waitserviceonline() {
	}

	/** minimal constructor */
	public Waitserviceonline(Customerservice customerservice) {
		this.customerservice = customerservice;
	}

	/** full constructor */
	public Waitserviceonline(Customerservice customerservice, Timestamp begintime, Integer csWindowtotal,
			Integer csWindowremain) {
		this.customerservice = customerservice;
		this.begintime = begintime;
		this.csWindowtotal = csWindowtotal;
		this.csWindowremain = csWindowremain;
	}

	// Property accessors
	@Id

	@Column(name = "cs_id", unique = true, nullable = false)

	public Integer getCsId() {
		return this.csId;
	}

	public void setCsId(Integer csId) {
		this.csId = csId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cs_id", unique = true, nullable = false, insertable = false, updatable = false)

	public Customerservice getCustomerservice() {
		return this.customerservice;
	}

	public void setCustomerservice(Customerservice customerservice) {
		this.customerservice = customerservice;
	}

	@Column(name = "begintime", length = 19)

	public Timestamp getBegintime() {
		return this.begintime;
	}

	public void setBegintime(Timestamp begintime) {
		this.begintime = begintime;
	}

	@Column(name = "cs_windowtotal")

	public Integer getCsWindowtotal() {
		return this.csWindowtotal;
	}

	public void setCsWindowtotal(Integer csWindowtotal) {
		this.csWindowtotal = csWindowtotal;
	}

	@Column(name = "cs_windowremain")

	public Integer getCsWindowremain() {
		return this.csWindowremain;
	}

	public void setCsWindowremain(Integer csWindowremain) {
		this.csWindowremain = csWindowremain;
	}

}