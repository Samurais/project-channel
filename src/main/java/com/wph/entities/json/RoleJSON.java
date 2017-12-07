package com.wph.entities.json;

public class RoleJSON {
	private Integer id;
	private String type;
	private String right;
	private Integer companyid;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

}
