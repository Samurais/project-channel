package com.wph.markov;

import java.util.List;

public class help {
	//此类不需要建表，仅负责帮助实现排列功能
	private int x;
	private long sum;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public long getSum() {
		return sum;
	}
	public void setSum(long sum) {
		this.sum = sum;
	}
	public static int findMinLocation(help[] hp){
		 //寻找最小值
	      Long min=hp[0].getSum();

	      int minx = hp[0].getX();
	      for(int i=1;i<hp.length;i++){
	          if(hp[i].getSum()<min){
	             min=hp[i].getSum();
	             minx = hp[i].getX();
	          }
	      }
	      return minx;
	  }

}
