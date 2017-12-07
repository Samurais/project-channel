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
 * Msg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "msg", catalog = "access")

public class Msg implements java.io.Serializable {

	// Fields

	private Integer id;
	private Customerservice customerservice;
	private Customer customer;
	private Msgtype msgtype;
	private Timestamp sendtime;
	private String content;
	private String sensitiveword;

	// Constructors

	/** default constructor */
	public Msg() {
	}

	/** minimal constructor */
	public Msg(Msgtype msgtype) {
		this.msgtype = msgtype;
	}

	/** full constructor */
	public Msg(Customerservice customerservice, Customer customer, Msgtype msgtype, Timestamp sendtime, String content,
			String sensitiveword) {
		this.customerservice = customerservice;
		this.customer = customer;
		this.msgtype = msgtype;
		this.sendtime = sendtime;
		this.content = content;
		this.sensitiveword = sensitiveword;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "msgtype_id", nullable = false)

	public Msgtype getMsgtype() {
		return this.msgtype;
	}

	public void setMsgtype(Msgtype msgtype) {
		this.msgtype = msgtype;
	}

	@Column(name = "sendtime", length = 19)

	public Timestamp getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}

	@Column(name = "content")

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "sensitiveword", length = 45)

	public String getSensitiveword() {
		return this.sensitiveword;
	}

	public void setSensitiveword(String sensitiveword) {
		this.sensitiveword = sensitiveword;
	}

}