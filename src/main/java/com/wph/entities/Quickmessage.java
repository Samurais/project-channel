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
 * Quickmessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "quickmessage", catalog = "access")

public class Quickmessage implements java.io.Serializable {

	// Fields

	private Integer id;
	private Customerservice customerservice;
	private String content;

	// Constructors

	/** default constructor */
	public Quickmessage() {
	}

	/** full constructor */
	public Quickmessage(Customerservice customerservice, String content) {
		this.customerservice = customerservice;
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
	@JoinColumn(name = "customerserviceid")

	public Customerservice getCustomerservice() {
		return this.customerservice;
	}

	public void setCustomerservice(Customerservice customerservice) {
		this.customerservice = customerservice;
	}

	@Column(name = "content")

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}