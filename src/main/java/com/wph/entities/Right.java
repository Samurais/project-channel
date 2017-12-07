package com.wph.entities;

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
 * Right entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "right", catalog = "access")

public class Right implements java.io.Serializable {

	// Fields

	private Integer id;
	private Company company;
	private String type;
	private Set<Roleright> rolerights = new HashSet<Roleright>(0);

	// Constructors

	/** default constructor */
	public Right() {
	}

	/** full constructor */
	public Right(Company company, String type, Set<Roleright> rolerights) {
		this.company = company;
		this.type = type;
		this.rolerights = rolerights;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "right")

	public Set<Roleright> getRolerights() {
		return this.rolerights;
	}

	public void setRolerights(Set<Roleright> rolerights) {
		this.rolerights = rolerights;
	}

}