package com.wph.entities.json;

public class HotWordJSON implements Comparable<HotWordJSON>{
	private Integer value;
	private String name;
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(HotWordJSON o) {
		
		return this.value - o.value;
	}
	
	
	
}
