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
 * Customerloginrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customerloginrecord", catalog = "access")

public class Customerloginrecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Customer customer;
	private String ipaddr;
	private String area;
	private Timestamp logintime;
	private String referer;
	private String browserinfo;
	private String hostname;
	private String systeminfo;
	private String macaddr;
	private String region;
	private String refererkeyword;

	// Constructors

	/** default constructor */
	public Customerloginrecord() {
	}

	/** minimal constructor */
	public Customerloginrecord(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Customerloginrecord(Integer id, Customer customer, String ipaddr, String area, Timestamp logintime,
			String referer, String browserinfo, String hostname, String systeminfo, String macaddr, String region,
			String refererkeyword) {
		this.id = id;
		this.customer = customer;
		this.ipaddr = ipaddr;
		this.area = area;
		this.logintime = logintime;
		this.referer = referer;
		this.browserinfo = browserinfo;
		this.hostname = hostname;
		this.systeminfo = systeminfo;
		this.macaddr = macaddr;
		this.region = region;
		this.refererkeyword = refererkeyword;
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

	@Column(name = "ipaddr")

	public String getIpaddr() {
		return this.ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	@Column(name = "area")

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "logintime", length = 19)

	public Timestamp getLogintime() {
		return this.logintime;
	}

	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}

	@Column(name = "referer")

	public String getReferer() {
		return this.referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	@Column(name = "browserinfo")

	public String getBrowserinfo() {
		return this.browserinfo;
	}

	public void setBrowserinfo(String browserinfo) {
		this.browserinfo = browserinfo;
	}

	@Column(name = "hostname", length = 45)

	public String getHostname() {
		return this.hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@Column(name = "systeminfo")

	public String getSysteminfo() {
		return this.systeminfo;
	}

	public void setSysteminfo(String systeminfo) {
		this.systeminfo = systeminfo;
	}

	@Column(name = "macaddr")

	public String getMacaddr() {
		return this.macaddr;
	}

	public void setMacaddr(String macaddr) {
		this.macaddr = macaddr;
	}

	@Column(name = "region")

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "refererkeyword")

	public String getRefererkeyword() {
		return this.refererkeyword;
	}

	public void setRefererkeyword(String refererkeyword) {
		this.refererkeyword = refererkeyword;
	}

}