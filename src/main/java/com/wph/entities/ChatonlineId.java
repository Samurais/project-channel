package com.wph.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ChatonlineId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class ChatonlineId implements java.io.Serializable {

	// Fields

	private Integer customerId;
	private Integer customerserviceId;

	// Constructors

	/** default constructor */
	public ChatonlineId() {
	}

	/** full constructor */
	public ChatonlineId(Integer customerId, Integer customerserviceId) {
		this.customerId = customerId;
		this.customerserviceId = customerserviceId;
	}

	// Property accessors

	@Column(name = "customer_id", nullable = false)

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Column(name = "customerservice_id", nullable = false)

	public Integer getCustomerserviceId() {
		return this.customerserviceId;
	}

	public void setCustomerserviceId(Integer customerserviceId) {
		this.customerserviceId = customerserviceId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ChatonlineId))
			return false;
		ChatonlineId castOther = (ChatonlineId) other;

		return ((this.getCustomerId() == castOther.getCustomerId()) || (this.getCustomerId() != null
				&& castOther.getCustomerId() != null && this.getCustomerId().equals(castOther.getCustomerId())))
				&& ((this.getCustomerserviceId() == castOther.getCustomerserviceId())
						|| (this.getCustomerserviceId() != null && castOther.getCustomerserviceId() != null
								&& this.getCustomerserviceId().equals(castOther.getCustomerserviceId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCustomerId() == null ? 0 : this.getCustomerId().hashCode());
		result = 37 * result + (getCustomerserviceId() == null ? 0 : this.getCustomerserviceId().hashCode());
		return result;
	}

}