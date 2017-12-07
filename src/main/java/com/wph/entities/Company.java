package com.wph.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Company entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "company", catalog = "access")

public class Company implements java.io.Serializable {

	// Fields

	private Integer cpId;
	private String cpPassword;
	private String cpName;
	private String cpAddress;
	private String cpPhone;
	private String cpMail;
	private String cpServicequantity;
	private String cpLevel;
	private String cpKonwledgebasepath;
	private Set<Customerservice> customerservices = new HashSet<Customerservice>(0);
	private Set<Companyrfm> companyrfms = new HashSet<Companyrfm>(0);
	private Set<Right> rights = new HashSet<Right>(0);
	private Set<Robotknowledgebase> robotknowledgebases = new HashSet<Robotknowledgebase>(0);
	private Set<Customer> customers = new HashSet<Customer>(0);
	private Set<Order> orders = new HashSet<Order>(0);
	private Set<Category> categories = new HashSet<Category>(0);
	private Set<Customerserviceright> customerservicerights = new HashSet<Customerserviceright>(0);
	private Set<Customerservicerole> customerserviceroles = new HashSet<Customerservicerole>(0);
	private Set<Role> roles = new HashSet<Role>(0);
	private Set<Knowledgebase> knowledgebases = new HashSet<Knowledgebase>(0);

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(Integer cpId) {
		this.cpId = cpId;
	}

	/** full constructor */
	public Company(Integer cpId, String cpPassword, String cpName, String cpAddress, String cpPhone, String cpMail,
			String cpServicequantity, String cpLevel, String cpKonwledgebasepath, Set<Customerservice> customerservices,
			Set<Companyrfm> companyrfms, Set<Right> rights, Set<Robotknowledgebase> robotknowledgebases,
			Set<Customer> customers, Set<Order> orders, Set<Category> categories,
			Set<Customerserviceright> customerservicerights, Set<Customerservicerole> customerserviceroles,
			Set<Role> roles, Set<Knowledgebase> knowledgebases) {
		this.cpId = cpId;
		this.cpPassword = cpPassword;
		this.cpName = cpName;
		this.cpAddress = cpAddress;
		this.cpPhone = cpPhone;
		this.cpMail = cpMail;
		this.cpServicequantity = cpServicequantity;
		this.cpLevel = cpLevel;
		this.cpKonwledgebasepath = cpKonwledgebasepath;
		this.customerservices = customerservices;
		this.companyrfms = companyrfms;
		this.rights = rights;
		this.robotknowledgebases = robotknowledgebases;
		this.customers = customers;
		this.orders = orders;
		this.categories = categories;
		this.customerservicerights = customerservicerights;
		this.customerserviceroles = customerserviceroles;
		this.roles = roles;
		this.knowledgebases = knowledgebases;
	}

	// Property accessors
	@Id

	@Column(name = "cp_id", unique = true, nullable = false)

	public Integer getCpId() {
		return this.cpId;
	}

	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}

	@Column(name = "cp_password", length = 45)

	public String getCpPassword() {
		return this.cpPassword;
	}

	public void setCpPassword(String cpPassword) {
		this.cpPassword = cpPassword;
	}

	@Column(name = "cp_name", length = 45)

	public String getCpName() {
		return this.cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	@Column(name = "cp_address")

	public String getCpAddress() {
		return this.cpAddress;
	}

	public void setCpAddress(String cpAddress) {
		this.cpAddress = cpAddress;
	}

	@Column(name = "cp_phone", length = 45)

	public String getCpPhone() {
		return this.cpPhone;
	}

	public void setCpPhone(String cpPhone) {
		this.cpPhone = cpPhone;
	}

	@Column(name = "cp_mail", length = 45)

	public String getCpMail() {
		return this.cpMail;
	}

	public void setCpMail(String cpMail) {
		this.cpMail = cpMail;
	}

	@Column(name = "cp_servicequantity", length = 45)

	public String getCpServicequantity() {
		return this.cpServicequantity;
	}

	public void setCpServicequantity(String cpServicequantity) {
		this.cpServicequantity = cpServicequantity;
	}

	@Column(name = "cp_level", length = 45)

	public String getCpLevel() {
		return this.cpLevel;
	}

	public void setCpLevel(String cpLevel) {
		this.cpLevel = cpLevel;
	}

	@Column(name = "cp_konwledgebasepath")

	public String getCpKonwledgebasepath() {
		return this.cpKonwledgebasepath;
	}

	public void setCpKonwledgebasepath(String cpKonwledgebasepath) {
		this.cpKonwledgebasepath = cpKonwledgebasepath;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")

	public Set<Customerservice> getCustomerservices() {
		return this.customerservices;
	}

	public void setCustomerservices(Set<Customerservice> customerservices) {
		this.customerservices = customerservices;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")

	public Set<Companyrfm> getCompanyrfms() {
		return this.companyrfms;
	}

	public void setCompanyrfms(Set<Companyrfm> companyrfms) {
		this.companyrfms = companyrfms;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")

	public Set<Right> getRights() {
		return this.rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")

	public Set<Robotknowledgebase> getRobotknowledgebases() {
		return this.robotknowledgebases;
	}

	public void setRobotknowledgebases(Set<Robotknowledgebase> robotknowledgebases) {
		this.robotknowledgebases = robotknowledgebases;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")

	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")

	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")

	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")

	public Set<Customerserviceright> getCustomerservicerights() {
		return this.customerservicerights;
	}

	public void setCustomerservicerights(Set<Customerserviceright> customerservicerights) {
		this.customerservicerights = customerservicerights;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")

	public Set<Customerservicerole> getCustomerserviceroles() {
		return this.customerserviceroles;
	}

	public void setCustomerserviceroles(Set<Customerservicerole> customerserviceroles) {
		this.customerserviceroles = customerserviceroles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")

	public Set<Knowledgebase> getKnowledgebases() {
		return this.knowledgebases;
	}

	public void setKnowledgebases(Set<Knowledgebase> knowledgebases) {
		this.knowledgebases = knowledgebases;
	}

}