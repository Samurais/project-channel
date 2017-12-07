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
 * Customerservicerightrole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customerservicerightrole", catalog = "access")

public class Customerservicerightrole implements java.io.Serializable {

	// Fields

	private CustomerservicerightroleId id;
	private Customerservicerole customerservicerole;
	private Customerserviceright customerserviceright;

	// Constructors

	/** default constructor */
	public Customerservicerightrole() {
	}

	/** full constructor */
	public Customerservicerightrole(CustomerservicerightroleId id, Customerservicerole customerservicerole,
			Customerserviceright customerserviceright) {
		this.id = id;
		this.customerservicerole = customerservicerole;
		this.customerserviceright = customerserviceright;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "customerserviceroleid", column = @Column(name = "customerserviceroleid", nullable = false) ),
			@AttributeOverride(name = "customerservicerightid", column = @Column(name = "customerservicerightid", nullable = false) ) })

	public CustomerservicerightroleId getId() {
		return this.id;
	}

	public void setId(CustomerservicerightroleId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerserviceroleid", nullable = false, insertable = false, updatable = false)

	public Customerservicerole getCustomerservicerole() {
		return this.customerservicerole;
	}

	public void setCustomerservicerole(Customerservicerole customerservicerole) {
		this.customerservicerole = customerservicerole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerservicerightid", nullable = false, insertable = false, updatable = false)

	public Customerserviceright getCustomerserviceright() {
		return this.customerserviceright;
	}

	public void setCustomerserviceright(Customerserviceright customerserviceright) {
		this.customerserviceright = customerserviceright;
	}

}