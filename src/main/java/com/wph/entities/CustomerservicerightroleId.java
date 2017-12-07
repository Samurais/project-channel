package com.wph.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CustomerservicerightroleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class CustomerservicerightroleId implements java.io.Serializable {

	// Fields

	private Integer customerserviceroleid;
	private Integer customerservicerightid;

	// Constructors

	/** default constructor */
	public CustomerservicerightroleId() {
	}

	/** full constructor */
	public CustomerservicerightroleId(Integer customerserviceroleid, Integer customerservicerightid) {
		this.customerserviceroleid = customerserviceroleid;
		this.customerservicerightid = customerservicerightid;
	}

	// Property accessors

	@Column(name = "customerserviceroleid", nullable = false)

	public Integer getCustomerserviceroleid() {
		return this.customerserviceroleid;
	}

	public void setCustomerserviceroleid(Integer customerserviceroleid) {
		this.customerserviceroleid = customerserviceroleid;
	}

	@Column(name = "customerservicerightid", nullable = false)

	public Integer getCustomerservicerightid() {
		return this.customerservicerightid;
	}

	public void setCustomerservicerightid(Integer customerservicerightid) {
		this.customerservicerightid = customerservicerightid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CustomerservicerightroleId))
			return false;
		CustomerservicerightroleId castOther = (CustomerservicerightroleId) other;

		return ((this.getCustomerserviceroleid() == castOther.getCustomerserviceroleid())
				|| (this.getCustomerserviceroleid() != null && castOther.getCustomerserviceroleid() != null
						&& this.getCustomerserviceroleid().equals(castOther.getCustomerserviceroleid())))
				&& ((this.getCustomerservicerightid() == castOther.getCustomerservicerightid())
						|| (this.getCustomerservicerightid() != null && castOther.getCustomerservicerightid() != null
								&& this.getCustomerservicerightid().equals(castOther.getCustomerservicerightid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCustomerserviceroleid() == null ? 0 : this.getCustomerserviceroleid().hashCode());
		result = 37 * result + (getCustomerservicerightid() == null ? 0 : this.getCustomerservicerightid().hashCode());
		return result;
	}

}