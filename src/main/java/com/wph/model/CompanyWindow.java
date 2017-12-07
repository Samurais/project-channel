package com.wph.model;

public class CompanyWindow {
	// 以每两个小时为单位的窗口优化
	private String time;
	private Integer requestcount;
	private Integer requestsuccess;
	private Integer requestfail;
	private Integer suggestservicecount;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getRequestcount() {
		return requestcount;
	}

	public void setRequestcount(Integer requestcount) {
		this.requestcount = requestcount;
	}

	public Integer getRequestsuccess() {
		return requestsuccess;
	}

	public void setRequestsuccess(Integer requestsuccess) {
		this.requestsuccess = requestsuccess;
	}

	public Integer getRequestfail() {
		return requestfail;
	}

	public void setRequestfail(Integer requestfail) {
		this.requestfail = requestfail;
	}

	public Integer getSuggestservicecount() {
		return suggestservicecount;
	}

	public void setSuggestservicecount(Integer suggestservicecount) {
		this.suggestservicecount = suggestservicecount;
	}

}
