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
 * Customerserviceloginrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customerserviceloginrecord", catalog = "access")

public class Customerserviceloginrecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Customerservice customerservice;
	private String loginip;
	private String loginarea;
	private Timestamp logintime;

	// Constructors

	/** default constructor */
	public Customerserviceloginrecord() {
	}

	/** minimal constructor */
	public Customerserviceloginrecord(Customerservice customerservice) {
		this.customerservice = customerservice;
	}

	/** full constructor */
	public Customerserviceloginrecord(Customerservice customerservice, String loginip, String loginarea,
			Timestamp logintime) {
		this.customerservice = customerservice;
		this.loginip = loginip;
		this.loginarea = loginarea;
		this.logintime = logintime;
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
	@JoinColumn(name = "costomerService_id", nullable = false)

	public Customerservice getCustomerservice() {
		return this.customerservice;
	}

	public void setCustomerservice(Customerservice customerservice) {
		this.customerservice = customerservice;
	}

	@Column(name = "loginip", length = 45)

	public String getLoginip() {
		return this.loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	@Column(name = "loginarea", length = 45)

	public String getLoginarea() {
		return this.loginarea;
	}

	public void setLoginarea(String loginarea) {
		this.loginarea = loginarea;
	}

	@Column(name = "logintime", length = 19)

	public Timestamp getLogintime() {
		return this.logintime;
	}

	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}

}