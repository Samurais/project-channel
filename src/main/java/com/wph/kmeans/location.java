package com.wph.kmeans;

import java.util.Date;

public class location {
	//数据点，假设是三维空间（r、f、m值）,需要Customer.java
	private int id;//默认为0，种子点ID标记自己，数据点的ID用于标识点群（即每个簇）
	private Rfm rfm;
	

	public Rfm getRfm() {
		return rfm;
	}
	public void setRfm(Rfm rfm) {
		this.rfm = rfm;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public location() {
		this.id=0;
	}
	
	
	
	public location(Rfm r) {//构造方法，把rfm相关信息传入到location中

		this.id=0;
		this.rfm = r;
	}
	public void set(Rfm r)//通过该方法把rfm的信息传入到location中
	{
		this.id=0;
		this.rfm = r;
	}
	public double distance(location b)
	{//比较俩个数据点的距离
		double dis = Math.sqrt((this.getRfm().getR()-b.getRfm().getR())*(this.getRfm().getR()-b.getRfm().getR())+
				(this.getRfm().getF()-b.getRfm().getF())*(this.getRfm().getF()-b.getRfm().getF())+
				(this.getRfm().getM()-b.getRfm().getM())*(this.getRfm().getM()-b.getRfm().getM())
				);
		return dis;		
	}
	
	public location mindis(location array[])
	{//比较该点与一组数组的最小距离。
		int length = array.length;
		double mindis = 999999999;
		int mini =0;
		for(int i=0;i<length;i++)
		{
			double dis = this.distance(array[i]);
			if(dis<mindis)
			{
				mindis = dis;
				mini = i;				
			}
		}
		return array[mini];
	}

	

}
