package com.wph.collaborativefiltering;

public class Customer {
	//ԭ���ֶ�
	private Integer ctId;
	
	//Ҫ��ӵ��ֶ�
	private double averageRating;//�ͻ�����ƽ��ֵ
	private double ratingSum;//�ͻ�������
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
