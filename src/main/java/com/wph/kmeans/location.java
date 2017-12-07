package com.wph.kmeans;

import java.util.Date;

public class location {
	//���ݵ㣬��������ά�ռ䣨r��f��mֵ��,��ҪCustomer.java
	private int id;//Ĭ��Ϊ0�����ӵ�ID����Լ������ݵ��ID���ڱ�ʶ��Ⱥ����ÿ���أ�
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
	
	
	
	public location(Rfm r) {//���췽������rfm�����Ϣ���뵽location��

		this.id=0;
		this.rfm = r;
	}
	public void set(Rfm r)//ͨ���÷�����rfm����Ϣ���뵽location��
	{
		this.id=0;
		this.rfm = r;
	}
	public double distance(location b)
	{//�Ƚ��������ݵ�ľ���
		double dis = Math.sqrt((this.getRfm().getR()-b.getRfm().getR())*(this.getRfm().getR()-b.getRfm().getR())+
				(this.getRfm().getF()-b.getRfm().getF())*(this.getRfm().getF()-b.getRfm().getF())+
				(this.getRfm().getM()-b.getRfm().getM())*(this.getRfm().getM()-b.getRfm().getM())
				);
		return dis;		
	}
	
	public location mindis(location array[])
	{//�Ƚϸõ���һ���������С���롣
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
