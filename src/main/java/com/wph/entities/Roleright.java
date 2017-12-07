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
 * Roleright entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roleright", catalog = "access")

public class Roleright implements java.io.Serializable {

	// Fields

	private RolerightId id;
	private Role role;
	private Right right;

	// Constructors

	/** default constructor */
	public Roleright() {
	}

	/** full constructor */
	public Roleright(RolerightId id, Role role, Right right) {
		this.id = id;
		this.role = role;
		this.right = right;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "roleid", column = @Column(name = "roleid", nullable = false) ),
			@AttributeOverride(name = "rightid", column = @Column(name = "rightid", nullable = false) ) })

	public RolerightId getId() {
		return this.id;
	}

	public void setId(RolerightId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid", nullable = false, insertable = false, updatable = false)

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rightid", nullable = false, insertable = false, updatable = false)

	public Right getRight() {
		return this.right;
	}

	public void setRight(Right right) {
		this.right = right;
	}

}