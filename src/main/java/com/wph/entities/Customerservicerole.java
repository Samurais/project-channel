package com.wph.entities;

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
 * Customerservicerole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customerservicerole", catalog = "access")

public class Customerservicerole implements java.io.Serializable {

	// Fields

	private Integer id;
	private Company company;
	private String type;
	private Integer level;
	private Set<Customerservicerightrole> customerservicerightroles = new HashSet<Customerservicerightrole>(0);
	private Set<Customerservice> customerservices = new HashSet<Customerservice>(0);

	// Constructors

	/** default constructor */
	public Customerservicerole() {
	}

	/** minimal constructor */
	public Customerservicerole(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Customerservicerole(Integer id, Company company, String type, Integer level,
			Set<Customerservicerightrole> customerservicerightroles, Set<Customerservice> customerservices) {
		this.id = id;
		this.company = company;
		this.type = type;
		this.level = level;
		this.customerservicerightroles = customerservicerightroles;
		this.customerservices = customerservices;
	}

	// Property accessors
	@Id

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyid")

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "type", length = 45)

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "level")

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerservicerole")

	public Set<Customerservicerightrole> getCustomerservicerightroles() {
		return this.customerservicerightroles;
	}

	public void setCustomerservicerightroles(Set<Customerservicerightrole> customerservicerightroles) {
		this.customerservicerightroles = customerservicerightroles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerservicerole")

	public Set<Customerservice> getCustomerservices() {
		return this.customerservices;
	}

	public void setCustomerservices(Set<Customerservice> customerservices) {
		this.customerservices = customerservices;
	}

}