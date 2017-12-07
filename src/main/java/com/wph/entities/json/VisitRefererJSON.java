package com.wph.entities.json;

public class VisitRefererJSON implements Comparable<VisitRefererJSON>{
	private String name;
	private Integer value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
	@Override
	public int compareTo(VisitRefererJSON o) {
		return this.value - o.value;
	}
	
	
}
