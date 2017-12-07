package com.wph.entities.json;

public class CompanyJSON {
	private Integer id;
	private String password;
	private String name;
	private String address;
	private String mail;
	private Integer servicequantity;
	private Integer level;
	private String knowledgebasepath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getServicequantity() {
		return servicequantity;
	}

	public void setServicequantity(Integer servicequantity) {
		this.servicequantity = servicequantity;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getKnowledgebasepath() {
		return knowledgebasepath;
	}

	public void setKnowledgebasepath(String knowledgebasepath) {
		this.knowledgebasepath = knowledgebasepath;
	}

}
