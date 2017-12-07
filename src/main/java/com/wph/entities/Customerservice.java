package com.wph.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Customerservice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customerservice", catalog = "access")

public class Customerservice implements java.io.Serializable {

	// Fields

	private Integer csId;
	private Company company;
	private Customerservicerole customerservicerole;
	private String csPassword;
	private String csUsername;
	private String csName;
	private String csPhone;
	private String csSex;
	private Timestamp csRegisttime;
	private Integer csWindow;
	private String csStatus;
	private Integer csLevel;
	private Set<Userrole> userroles = new HashSet<Userrole>(0);
	private Set<Knowledgebase> knowledgebases = new HashSet<Knowledgebase>(0);
	private Set<Conversation> conversations = new HashSet<Conversation>(0);
	private Set<Customerserviceonline> customerserviceonlines = new HashSet<Customerserviceonline>(0);
	private Set<Customerserviceloginrecord> customerserviceloginrecords = new HashSet<Customerserviceloginrecord>(0);
	private Set<Msg> msgs = new HashSet<Msg>(0);
	private Set<Chatonline> chatonlines = new HashSet<Chatonline>(0);
	private Set<Customerrfmsample> customerrfmsamples = new HashSet<Customerrfmsample>(0);
	private Set<Waitserviceonline> waitserviceonlines = new HashSet<Waitserviceonline>(0);

	// Constructors

	/** default constructor */
	public Customerservice() {
	}

	/** minimal constructor */
	public Customerservice(Integer csId, String csPassword, String csUsername) {
		this.csId = csId;
		this.csPassword = csPassword;
		this.csUsername = csUsername;
	}

	/** full constructor */
	public Customerservice(Integer csId, Company company, Customerservicerole customerservicerole, String csPassword,
			String csUsername, String csName, String csPhone, String csSex, Timestamp csRegisttime, Integer csWindow,
			String csStatus, Integer csLevel, Set<Userrole> userroles, Set<Knowledgebase> knowledgebases,
			Set<Conversation> conversations, Set<Customerserviceonline> customerserviceonlines,
			Set<Customerserviceloginrecord> customerserviceloginrecords, Set<Msg> msgs, Set<Chatonline> chatonlines,
			Set<Customerrfmsample> customerrfmsamples, Set<Waitserviceonline> waitserviceonlines) {
		this.csId = csId;
		this.company = company;
		this.customerservicerole = customerservicerole;
		this.csPassword = csPassword;
		this.csUsername = csUsername;
		this.csName = csName;
		this.csPhone = csPhone;
		this.csSex = csSex;
		this.csRegisttime = csRegisttime;
		this.csWindow = csWindow;
		this.csStatus = csStatus;
		this.csLevel = csLevel;
		this.userroles = userroles;
		this.knowledgebases = knowledgebases;
		this.conversations = conversations;
		this.customerserviceonlines = customerserviceonlines;
		this.customerserviceloginrecords = customerserviceloginrecords;
		this.msgs = msgs;
		this.chatonlines = chatonlines;
		this.customerrfmsamples = customerrfmsamples;
		this.waitserviceonlines = waitserviceonlines;
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
	@JoinColumn(name = "company_id")

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cs_role")

	public Customerservicerole getCustomerservicerole() {
		return this.customerservicerole;
	}

	public void setCustomerservicerole(Customerservicerole customerservicerole) {
		this.customerservicerole = customerservicerole;
	}

	@Column(name = "cs_password", nullable = false, length = 45)

	public String getCsPassword() {
		return this.csPassword;
	}

	public void setCsPassword(String csPassword) {
		this.csPassword = csPassword;
	}

	@Column(name = "cs_username", nullable = false, length = 45)

	public String getCsUsername() {
		return this.csUsername;
	}

	public void setCsUsername(String csUsername) {
		this.csUsername = csUsername;
	}

	@Column(name = "cs_name", length = 45)

	public String getCsName() {
		return this.csName;
	}

	public void setCsName(String csName) {
		this.csName = csName;
	}

	@Column(name = "cs_phone", length = 45)

	public String getCsPhone() {
		return this.csPhone;
	}

	public void setCsPhone(String csPhone) {
		this.csPhone = csPhone;
	}

	@Column(name = "cs_sex", length = 2)

	public String getCsSex() {
		return this.csSex;
	}

	public void setCsSex(String csSex) {
		this.csSex = csSex;
	}

	@Column(name = "cs_registtime", length = 19)

	public Timestamp getCsRegisttime() {
		return this.csRegisttime;
	}

	public void setCsRegisttime(Timestamp csRegisttime) {
		this.csRegisttime = csRegisttime;
	}

	@Column(name = "cs_window")

	public Integer getCsWindow() {
		return this.csWindow;
	}

	public void setCsWindow(Integer csWindow) {
		this.csWindow = csWindow;
	}

	@Column(name = "cs_status", length = 45)

	public String getCsStatus() {
		return this.csStatus;
	}

	public void setCsStatus(String csStatus) {
		this.csStatus = csStatus;
	}

	@Column(name = "cs_level")

	public Integer getCsLevel() {
		return this.csLevel;
	}

	public void setCsLevel(Integer csLevel) {
		this.csLevel = csLevel;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerservice")

	public Set<Userrole> getUserroles() {
		return this.userroles;
	}

	public void setUserroles(Set<Userrole> userroles) {
		this.userroles = userroles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerservice")

	public Set<Knowledgebase> getKnowledgebases() {
		return this.knowledgebases;
	}

	public void setKnowledgebases(Set<Knowledgebase> knowledgebases) {
		this.knowledgebases = knowledgebases;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerservice")

	public Set<Conversation> getConversations() {
		return this.conversations;
	}

	public void setConversations(Set<Conversation> conversations) {
		this.conversations = conversations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerservice")

	public Set<Customerserviceonline> getCustomerserviceonlines() {
		return this.customerserviceonlines;
	}

	public void setCustomerserviceonlines(Set<Customerserviceonline> customerserviceonlines) {
		this.customerserviceonlines = customerserviceonlines;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerservice")

	public Set<Customerserviceloginrecord> getCustomerserviceloginrecords() {
		return this.customerserviceloginrecords;
	}

	public void setCustomerserviceloginrecords(Set<Customerserviceloginrecord> customerserviceloginrecords) {
		this.customerserviceloginrecords = customerserviceloginrecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerservice")

	public Set<Msg> getMsgs() {
		return this.msgs;
	}

	public void setMsgs(Set<Msg> msgs) {
		this.msgs = msgs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerservice")

	public Set<Chatonline> getChatonlines() {
		return this.chatonlines;
	}

	public void setChatonlines(Set<Chatonline> chatonlines) {
		this.chatonlines = chatonlines;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerservice")

	public Set<Customerrfmsample> getCustomerrfmsamples() {
		return this.customerrfmsamples;
	}

	public void setCustomerrfmsamples(Set<Customerrfmsample> customerrfmsamples) {
		this.customerrfmsamples = customerrfmsamples;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerservice")

	public Set<Waitserviceonline> getWaitserviceonlines() {
		return this.waitserviceonlines;
	}

	public void setWaitserviceonlines(Set<Waitserviceonline> waitserviceonlines) {
		this.waitserviceonlines = waitserviceonlines;
	}

}