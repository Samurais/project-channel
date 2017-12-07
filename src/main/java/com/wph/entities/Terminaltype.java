package com.wph.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Terminaltype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "terminaltype", catalog = "access")

public class Terminaltype implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private Set<Terminal> terminals = new HashSet<Terminal>(0);

	// Constructors

	/** default constructor */
	public Terminaltype() {
	}

	/** minimal constructor */
	public Terminaltype(String type) {
		this.type = type;
	}

	/** full constructor */
	public Terminaltype(String type, Set<Terminal> terminals) {
		this.type = type;
		this.terminals = terminals;
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

	@Column(name = "type", nullable = false, length = 45)

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "terminaltype")

	public Set<Terminal> getTerminals() {
		return this.terminals;
	}

	public void setTerminals(Set<Terminal> terminals) {
		this.terminals = terminals;
	}

}