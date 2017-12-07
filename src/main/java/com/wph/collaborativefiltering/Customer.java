package com.wph.collaborativefiltering;

public class Customer {
	//原有字段
	private Integer ctId;
	
	//要添加的字段
	private double averageRating;//客户评价平均值
	private double ratingSum;//客户评价数
	public Integer getCtId() {
		return ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public double getRatingSum() {
		return ratingSum;
	}

	public void setRatingSum(double ratingSum) {
		this.ratingSum = ratingSum;
	}
	

}
