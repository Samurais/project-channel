package com.wph.entities.json;

import java.util.HashSet;
import java.util.Set;

import com.wph.entities.Category;
import com.wph.entities.Order;

public class ProductJSON {

	private Integer id;
	private Integer category_id;
	private String name;
	private Integer price;
	private String pic;
	private String remark;
	private String xremark;
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getXremark() {
		return xremark;
	}

	public void setXremark(String xremark) {
		this.xremark = xremark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
