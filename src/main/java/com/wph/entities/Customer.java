package com.wph.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer", catalog = "access")

public class Customer implements java.io.Serializable {

	// Fields

	private Integer ctId;
	private Company company;
	private String ctPassword;
	private String ctUsername;
	private String ctName;
	private String ctPhone;
	private String ctMailbox;
	private String ctSex;
	private String ctIp;
	private Double ctLevel;
	private Timestamp ctRegisttime;
	private Set<Chatonline> chatonlines = new HashSet<Chatonline>(0);
	private Set<Customerloginrecord> customerloginrecords = new HashSet<Customerloginrecord>(0);
	private Set<Msg> msgs = new HashSet<Msg>(0);
	private Set<Conversation> conversations = new HashSet<Conversation>(0);
	private Set<Customeronline> customeronlines = new HashSet<Customeronline>(0);
	private Set<Waitonline> waitonlines = new HashSet<Waitonline>(0);
	private Set<Customerrfm> customerrfms = new HashSet<Customerrfm>(0);

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** minimal constructor */
	public Customer(String ctPassword) {
		this.ctPassword = ctPassword;
	}

	/** full constructor */
	public Customer(Company company, String ctPassword, String ctUsername, String ctName, String ctPhone,
			String ctMailbox, String ctSex, String ctIp, Double ctLevel, Timestamp ctRegisttime,
			Set<Chatonline> chatonlines, Set<Customerloginrecord> customerloginrecords, Set<Msg> msgs,
			Set<Conversation> conversations, Set<Customeronline> customeronlines, Set<Waitonline> waitonlines,
			Set<Customerrfm> customerrfms) {
		this.company = company;
		this.ctPassword = ctPassword;
		this.ctUsername = ctUsername;
		this.ctName = ctName;
		this.ctPhone = ctPhone;
		this.ctMailbox = ctMailbox;
		this.ctSex = ctSex;
		this.ctIp = ctIp;
		this.ctLevel = ctLevel;
		this.ctRegisttime = ctRegisttime;
		this.chatonlines = chatonlines;
		this.customerloginrecords = customerloginrecords;
		this.msgs = msgs;
		this.conversations = conversations;
		this.customeronlines = customeronlines;
		this.waitonlines = waitonlines;
		this.customerrfms = customerrfms;
	}

	// Property accessors
	@Id

	@Column(name = "ct_id", unique = true, nullable = false)

	public Integer getCtId() {
		return this.ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "ct_password", nullable = false, length = 45)

	public String getCtPassword() {
		return this.ctPassword;
	}

	public void setCtPassword(String ctPassword) {
		this.ctPassword = ctPassword;
	}

	@Column(name = "ct_username", length = 45)

	public String getCtUsername() {
		return this.ctUsername;
	}

	public void setCtUsername(String ctUsername) {
		this.ctUsername = ctUsername;
	}

	@Column(name = "ct_name", length = 45)

	public String getCtName() {
		return this.ctName;
	}

	public void setCtName(String ctName) {
		this.ctName = ctName;
	}

	@Column(name = "ct_phone", length = 45)

	public String getCtPhone() {
		return this.ctPhone;
	}

	public void setCtPhone(String ctPhone) {
		this.ctPhone = ctPhone;
	}

	@Column(name = "ct_mailbox", length = 45)

	public String getCtMailbox() {
		return this.ctMailbox;
	}

	public void setCtMailbox(String ctMailbox) {
		this.ctMailbox = ctMailbox;
	}

	@Column(name = "ct_sex", length = 2)

	public String getCtSex() {
		return this.ctSex;
	}

	public void setCtSex(String ctSex) {
		this.ctSex = ctSex;
	}

	@Column(name = "ct_ip", length = 45)

	public String getCtIp() {
		return this.ctIp;
	}

	public void setCtIp(String ctIp) {
		this.ctIp = ctIp;
	}

	@Column(name = "ct_level", precision = 22, scale = 0)

	public Double getCtLevel() {
		return this.ctLevel;
	}

	public void setCtLevel(Double ctLevel) {
		this.ctLevel = ctLevel;
	}

	@Column(name = "ct_registtime", length = 19)

	public Timestamp getCtRegisttime() {
		return this.ctRegisttime;
	}

	public void setCtRegisttime(Timestamp ctRegisttime) {
		this.ctRegisttime = ctRegisttime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")

	public Set<Chatonline> getChatonlines() {
		return this.chatonlines;
	}

	public void setChatonlines(Set<Chatonline> chatonlines) {
		this.chatonlines = chatonlines;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")

	public Set<Customerloginrecord> getCustomerloginrecords() {
		return this.customerloginrecords;
	}

	public void setCustomerloginrecords(Set<Customerloginrecord> customerloginrecords) {
		this.customerloginrecords = customerloginrecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")

	public Set<Msg> getMsgs() {
		return this.msgs;
	}

	public void setMsgs(Set<Msg> msgs) {
		this.msgs = msgs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")

	public Set<Conversation> getConversations() {
		return this.conversations;
	}

	public void setConversations(Set<Conversation> conversations) {
		this.conversations = conversations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")

	public Set<Customeronline> getCustomeronlines() {
		return this.customeronlines;
	}

	public void setCustomeronlines(Set<Customeronline> customeronlines) {
		this.customeronlines = customeronlines;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")

	public Set<Waitonline> getWaitonlines() {
		return this.waitonlines;
	}

	public void setWaitonlines(Set<Waitonline> waitonlines) {
		this.waitonlines = waitonlines;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")

	public Set<Customerrfm> getCustomerrfms() {
		return this.customerrfms;
	}

	public void setCustomerrfms(Set<Customerrfm> customerrfms) {
		this.customerrfms = customerrfms;
	}

}