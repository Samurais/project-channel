package com.wph.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RolerightId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class RolerightId implements java.io.Serializable {

	// Fields

	private Integer roleid;
	private Integer rightid;

	// Constructors

	/** default constructor */
	public RolerightId() {
	}

	/** full constructor */
	public RolerightId(Integer roleid, Integer rightid) {
		this.roleid = roleid;
		this.rightid = rightid;
	}

	// Property accessors

	@Column(name = "roleid", nullable = false)

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	@Column(name = "rightid", nullable = false)

	public Integer getRightid() {
		return this.rightid;
	}

	public void setRightid(Integer rightid) {
		this.rightid = rightid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RolerightId))
			return false;
		RolerightId castOther = (RolerightId) other;

		return ((this.getRoleid() == castOther.getRoleid()) || (this.getRoleid() != null
				&& castOther.getRoleid() != null && this.getRoleid().equals(castOther.getRoleid())))
				&& ((this.getRightid() == castOther.getRightid()) || (this.getRightid() != null
						&& castOther.getRightid() != null && this.getRightid().equals(castOther.getRightid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getRoleid() == null ? 0 : this.getRoleid().hashCode());
		result = 37 * result + (getRightid() == null ? 0 : this.getRightid().hashCode());
		return result;
	}

}