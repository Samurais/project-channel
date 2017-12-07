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
 * Customerserviceright entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customerserviceright", catalog = "access")

public class Customerserviceright implements java.io.Serializable {

	// Fields

	private Integer id;
	private Company company;
	private String type;
	private Set<Customerservicerightrole> customerservicerightroles = new HashSet<Customerservicerightrole>(0);

	// Constructors

	/** default constructor */
	public Customerserviceright() {
	}

	/** minimal constructor */
	public Customerserviceright(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Customerserviceright(Integer id, Company company, String type,
			Set<Customerservicerightrole> customerservicerightroles) {
		this.id = id;
		this.company = company;
		this.type = type;
		this.customerservicerightroles = customerservicerightroles;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerserviceright")

	public Set<Customerservicerightrole> getCustomerservicerightroles() {
		return this.customerservicerightroles;
	}

	public void setCustomerservicerightroles(Set<Customerservicerightrole> customerservicerightroles) {
		this.customerservicerightroles = customerservicerightroles;
	}

}