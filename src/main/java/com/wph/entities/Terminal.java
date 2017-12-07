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
 * Terminal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "terminal", catalog = "access")

public class Terminal implements java.io.Serializable {

	// Fields

	private Integer id;
	private Terminaltype terminaltype;
	private String identification;
	private String status;
	private Integer statusid;
	private Set<Customeronline> customeronlines = new HashSet<Customeronline>(0);
	private Set<Customerserviceonline> customerserviceonlines = new HashSet<Customerserviceonline>(0);

	// Constructors

	/** default constructor */
	public Terminal() {
	}

	/** minimal constructor */
	public Terminal(Terminaltype terminaltype, String status, Integer statusid) {
		this.terminaltype = terminaltype;
		this.status = status;
		this.statusid = statusid;
	}

	/** full constructor */
	public Terminal(Terminaltype terminaltype, String identification, String status, Integer statusid,
			Set<Customeronline> customeronlines, Set<Customerserviceonline> customerserviceonlines) {
		this.terminaltype = terminaltype;
		this.identification = identification;
		this.status = status;
		this.statusid = statusid;
		this.customeronlines = customeronlines;
		this.customerserviceonlines = customerserviceonlines;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "terminaltype_id", nullable = false)

	public Terminaltype getTerminaltype() {
		return this.terminaltype;
	}

	public void setTerminaltype(Terminaltype terminaltype) {
		this.terminaltype = terminaltype;
	}

	@Column(name = "identification", length = 45)

	public String getIdentification() {
		return this.identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	@Column(name = "status", nullable = false, length = 3)

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "statusid", nullable = false)

	public Integer getStatusid() {
		return this.statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "terminal")

	public Set<Customeronline> getCustomeronlines() {
		return this.customeronlines;
	}

	public void setCustomeronlines(Set<Customeronline> customeronlines) {
		this.customeronlines = customeronlines;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "terminal")

	public Set<Customerserviceonline> getCustomerserviceonlines() {
		return this.customerserviceonlines;
	}

	public void setCustomerserviceonlines(Set<Customerserviceonline> customerserviceonlines) {
		this.customerserviceonlines = customerserviceonlines;
	}

}