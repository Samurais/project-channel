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
 * Customerrfm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customerrfm", catalog = "access")

public class Customerrfm implements java.io.Serializable {

	// Fields

	private Integer id;
	private Customer customer;
	private Timestamp lastbuytime;
	private Integer monthbuytimes;
	private Integer lastmonthbuytimes;
	private Integer monthbuysum;
	private Integer lastmonthbuysum;

	// Constructors

	/** default constructor */
	public Customerrfm() {
	}

	/** full constructor */
	public Customerrfm(Customer customer, Timestamp lastbuytime, Integer monthbuytimes, Integer lastmonthbuytimes,
			Integer monthbuysum, Integer lastmonthbuysum) {
		this.customer = customer;
		this.lastbuytime = lastbuytime;
		this.monthbuytimes = monthbuytimes;
		this.lastmonthbuytimes = lastmonthbuytimes;
		this.monthbuysum = monthbuysum;
		this.lastmonthbuysum = lastmonthbuysum;
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
	@JoinColumn(name = "customer_id")

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "lastbuytime", length = 19)

	public Timestamp getLastbuytime() {
		return this.lastbuytime;
	}

	public void setLastbuytime(Timestamp lastbuytime) {
		this.lastbuytime = lastbuytime;
	}

	@Column(name = "monthbuytimes")

	public Integer getMonthbuytimes() {
		return this.monthbuytimes;
	}

	public void setMonthbuytimes(Integer monthbuytimes) {
		this.monthbuytimes = monthbuytimes;
	}

	@Column(name = "lastmonthbuytimes")

	public Integer getLastmonthbuytimes() {
		return this.lastmonthbuytimes;
	}

	public void setLastmonthbuytimes(Integer lastmonthbuytimes) {
		this.lastmonthbuytimes = lastmonthbuytimes;
	}

	@Column(name = "monthbuysum")

	public Integer getMonthbuysum() {
		return this.monthbuysum;
	}

	public void setMonthbuysum(Integer monthbuysum) {
		this.monthbuysum = monthbuysum;
	}

	@Column(name = "lastmonthbuysum")

	public Integer getLastmonthbuysum() {
		return this.lastmonthbuysum;
	}

	public void setLastmonthbuysum(Integer lastmonthbuysum) {
		this.lastmonthbuysum = lastmonthbuysum;
	}

}