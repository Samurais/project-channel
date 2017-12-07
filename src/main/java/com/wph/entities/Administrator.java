package com.wph.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Administrator entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "administrator", catalog = "access")

public class Administrator implements java.io.Serializable {

	// Fields

	private Integer adId;
	private String adPassword;
	private String adUsername;
	private String adPhone;
	private String adMailbox;

	// Constructors

	/** default constructor */
	public Administrator() {
	}

	/** full constructor */
	public Administrator(String adPassword, String adUsername, String adPhone, String adMailbox) {
		this.adPassword = adPassword;
		this.adUsername = adUsername;
		this.adPhone = adPhone;
		this.adMailbox = adMailbox;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "ad_id", unique = true, nullable = false)

	public Integer getAdId() {
		return this.adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	@Column(name = "ad_password", length = 45)

	public String getAdPassword() {
		return this.adPassword;
	}

	public void setAdPassword(String adPassword) {
		this.adPassword = adPassword;
	}

	@Column(name = "ad_username", length = 45)

	public String getAdUsername() {
		return this.adUsername;
	}

	public void setAdUsername(String adUsername) {
		this.adUsername = adUsername;
	}

	@Column(name = "ad_phone", length = 45)

	public String getAdPhone() {
		return this.adPhone;
	}

	public void setAdPhone(String adPhone) {
		this.adPhone = adPhone;
	}

	@Column(name = "ad_mailbox", length = 45)

	public String getAdMailbox() {
		return this.adMailbox;
	}

	public void setAdMailbox(String adMailbox) {
		this.adMailbox = adMailbox;
	}

}