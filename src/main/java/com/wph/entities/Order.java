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
 * Order entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "order", catalog = "access")

public class Order implements java.io.Serializable {

	// Fields

	private Integer id;
	private Company company;
	private Product product;
	private Timestamp time;
	private Integer customerserviceId;
	private Integer customerId;
	private Timestamp demandtime;
	private String demandaddr;
	private Integer demandphone;
	private String demandotherrequest;
	private String status;

	// Constructors

	/** default constructor */
	public Order() {
	}

	/** full constructor */
	public Order(Company company, Product product, Timestamp time, Integer customerserviceId, Integer customerId,
			Timestamp demandtime, String demandaddr, Integer demandphone, String demandotherrequest, String status) {
		this.company = company;
		this.product = product;
		this.time = time;
		this.customerserviceId = customerserviceId;
		this.customerId = customerId;
		this.demandtime = demandtime;
		this.demandaddr = demandaddr;
		this.demandphone = demandphone;
		this.demandotherrequest = demandotherrequest;
		this.status = status;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "time", length = 19)

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Column(name = "customerservice_id")

	public Integer getCustomerserviceId() {
		return this.customerserviceId;
	}

	public void setCustomerserviceId(Integer customerserviceId) {
		this.customerserviceId = customerserviceId;
	}

	@Column(name = "customer_id")

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Column(name = "demandtime", length = 19)

	public Timestamp getDemandtime() {
		return this.demandtime;
	}

	public void setDemandtime(Timestamp demandtime) {
		this.demandtime = demandtime;
	}

	@Column(name = "demandaddr")

	public String getDemandaddr() {
		return this.demandaddr;
	}

	public void setDemandaddr(String demandaddr) {
		this.demandaddr = demandaddr;
	}

	@Column(name = "demandphone")

	public Integer getDemandphone() {
		return this.demandphone;
	}

	public void setDemandphone(Integer demandphone) {
		this.demandphone = demandphone;
	}

	@Column(name = "demandotherrequest")

	public String getDemandotherrequest() {
		return this.demandotherrequest;
	}

	public void setDemandotherrequest(String demandotherrequest) {
		this.demandotherrequest = demandotherrequest;
	}

	@Column(name = "status", length = 3)

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}