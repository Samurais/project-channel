package com.wph.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Msgtype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "msgtype", catalog = "access")

public class Msgtype implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private Set<Msg> msgs = new HashSet<Msg>(0);

	// Constructors

	/** default constructor */
	public Msgtype() {
	}

	/** minimal constructor */
	public Msgtype(String type) {
		this.type = type;
	}

	/** full constructor */
	public Msgtype(String type, Set<Msg> msgs) {
		this.type = type;
		this.msgs = msgs;
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

	@Column(name = "type", nullable = false, length = 45)

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "msgtype")

	public Set<Msg> getMsgs() {
		return this.msgs;
	}

	public void setMsgs(Set<Msg> msgs) {
		this.msgs = msgs;
	}

}