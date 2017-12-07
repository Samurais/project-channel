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
 * Robotknowledgebase entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "robotknowledgebase", catalog = "access")

public class Robotknowledgebase implements java.io.Serializable {

	// Fields

	private Integer id;
	private Company company;
	private String title;
	private String category;
	private Timestamp createtime;
	private String content;
	private Integer searchcount;

	// Constructors

	/** default constructor */
	public Robotknowledgebase() {
	}

	/** full constructor */
	public Robotknowledgebase(Company company, String title, String category, Timestamp createtime, String content,
			Integer searchcount) {
		this.company = company;
		this.title = title;
		this.category = category;
		this.createtime = createtime;
		this.content = content;
		this.searchcount = searchcount;
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

	@Column(name = "title", length = 45)

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "category", length = 45)

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "createtime", length = 19)

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "content", length = 16777215)

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "searchcount")

	public Integer getSearchcount() {
		return this.searchcount;
	}

	public void setSearchcount(Integer searchcount) {
		this.searchcount = searchcount;
	}

}