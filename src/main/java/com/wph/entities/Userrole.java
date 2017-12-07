package com.wph.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Userrole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "userrole", catalog = "access")

public class Userrole implements java.io.Serializable {

	// Fields

	private UserroleId id;
	private Customerservice customerservice;
	private Role role;

	// Constructors

	/** default constructor */
	public Userrole() {
	}

	/** full constructor */
	public Userrole(UserroleId id, Customerservice customerservice, Role role) {
		this.id = id;
		this.customerservice = customerservice;
		this.role = role;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "userid", column = @Column(name = "userid", nullable = false) ),
			@AttributeOverride(name = "roleid", column = @Column(name = "roleid", nullable = false) ) })

	public UserroleId getId() {
		return this.id;
	}

	public void setId(UserroleId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false, insertable = false, updatable = false)

	public Customerservice getCustomerservice() {
		return this.customerservice;
	}

	public void setCustomerservice(Customerservice customerservice) {
		this.customerservice = customerservice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid", nullable = false, insertable = false, updatable = false)

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}