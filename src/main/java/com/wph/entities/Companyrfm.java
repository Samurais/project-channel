package com.wph.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Companyrfm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "companyrfm", catalog = "access")

public class Companyrfm implements java.io.Serializable {

	// Fields

	private Integer id;
	private Company company;
	private Integer a0;
	private Integer a1r;
	private Integer a2f;
	private Integer a3m;
	private Timestamp updatetime;

	// Constructors

	/** default constructor */
	public Companyrfm() {
	}

	/** full constructor */
	public Companyrfm(Company company, Integer a0, Integer a1r, Integer a2f, Integer a3m, Timestamp updatetime) {
		this.company = company;
		this.a0 = a0;
		this.a1r = a1r;
		this.a2f = a2f;
		this.a3m = a3m;
		this.updatetime = updatetime;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "a0")

	public Integer getA0() {
		return this.a0;
	}

	public void setA0(Integer a0) {
		this.a0 = a0;
	}

	@Column(name = "a1r")

	public Integer getA1r() {
		return this.a1r;
	}

	public void setA1r(Integer a1r) {
		this.a1r = a1r;
	}

	@Column(name = "a2f")

	public Integer getA2f() {
		return this.a2f;
	}

	public void setA2f(Integer a2f) {
		this.a2f = a2f;
	}

	@Column(name = "a3m")

	public Integer getA3m() {
		return this.a3m;
	}

	public void setA3m(Integer a3m) {
		this.a3m = a3m;
	}

	@Column(name = "updatetime", length = 19)

	public Timestamp getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

}