package com.wph.model;

public class ServiceActive {
	private Integer id;
	private Boolean isonline;
	private Integer currentservice;
	private Integer haveservice;
	private String firstlandtime;
	
	
	public ServiceActive() {
		super();
	}

	


	public ServiceActive(Integer id, Boolean isonline, Integer currentservice, Integer haveservice,
			String firstlandtime) {
		super();
		this.id = id;
		this.isonline = isonline;
		this.currentservice = currentservice;
		this.haveservice = haveservice;
		this.firstlandtime = firstlandtime;
	}




	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Boolean getIsonline() {
		return isonline;
	}



	public void setIsonline(Boolean isonline) {
		this.isonline = isonline;
	}



	public Integer getCurrentservice() {
		return currentservice;
	}



	public void setCurrentservice(Integer currentservice) {
		this.currentservice = currentservice;
	}



	public Integer getHaveservice() {
		return haveservice;
	}



	public void setHaveservice(Integer haveservice) {
		this.haveservice = haveservice;
	}



	public String getFirstlandtime() {
		return firstlandtime;
	}



	public void setFirstlandtime(String firstlandtime) {
		this.firstlandtime = firstlandtime;
	}


	
	
	
	
}
