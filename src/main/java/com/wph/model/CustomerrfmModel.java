package com.wph.model;

import java.sql.Timestamp;

public class CustomerrfmModel {
	private Integer id;
	private Timestamp lastbuytime;
	private Integer monthbuytimes;
	private Integer lastmonthbuytimes;
	private Integer monthbuysum;
	private Integer lastmonthbuysum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getLastbuytime() {
		return lastbuytime;
	}
	public void setLastbuytime(Timestamp lastbuytime) {
		this.lastbuytime = lastbuytime;
	}
	public Integer getMonthbuytimes() {
		return monthbuytimes;
	}
	public void setMonthbuytimes(Integer monthbuytimes) {
		this.monthbuytimes = monthbuytimes;
	}
	public Integer getLastmonthbuytimes() {
		return lastmonthbuytimes;
	}
	public void setLastmonthbuytimes(Integer lastmonthbuytimes) {
		this.lastmonthbuytimes = lastmonthbuytimes;
	}
	public Integer getMonthbuysum() {
		return monthbuysum;
	}
	public void setMonthbuysum(Integer monthbuysum) {
		this.monthbuysum = monthbuysum;
	}
	public Integer getLastmonthbuysum() {
		return lastmonthbuysum;
	}
	public void setLastmonthbuysum(Integer lastmonthbuysum) {
		this.lastmonthbuysum = lastmonthbuysum;
	}
	
	
}
