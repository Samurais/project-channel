//package com.wph.kmeans;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//import java.util.Set;
//
//public class main {
////生成样品输入：顾客的最后一次购买的时间、
////当月购买次数、上月购买次数、当月购买总额、上月购买总额、rfm值
//	
////kmeans算法输入：顾客的最后一次购买的时间、
////当月购买次数、上月购买次数、当月购买总额、上月购买总额、k值
////种群ID：从0开始，k-1结束
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		long start=System.currentTimeMillis();
//		
//		//导入顾客数据
//		 Customerrfm[] ct = new Customerrfm[50]; 
//		 Random r = new Random();
//		for(int i=0;i<ct.length;i++)
//		{
//
//			ct[i] = new Customerrfm();
//			ct[i].setId(i);
//			ct[i].setLastbuytime(new Timestamp((new Date().getTime()/1000/24/3600-(15-r.nextInt(10)))*1000*24*3600));
//			ct[i].setLastmonthbuytimes(r.nextInt(200));
//			ct[i].setMonthbuytimes(r.nextInt(50));
//			ct[i].setLastmonthbuysum(r.nextInt(40000));
//			ct[i].setMonthbuysum(r.nextInt(10000));
//			
//		}
//
//
//		
//		//数据生成完毕
//		
//		
//		
//		int k = 10;//k值不能设置过大，设置过大会加大运行时间。
//
//		if(ct.length<=0){
//			//注意数据点为0的情况（数据库中没有数据）
//			return;
//		}
//		if(k>ct.length)
//		{//k值不能比数据总数大
//			System.out.println("K值过大");
//			return;
//		}
//		if(k<=0){
//			//k值不能小于0
//			System.out.println("K值过小");
//			return;
//		}
//
//		kmeans ks = new kmeans();
//		Map<Integer,Double> map = ks.kmeans(ct,k); //K-Means算法，参数ct表示customer数组，k表示输入的k
//
//	
//		for(int i=0;i<ct.length;i++){
//			System.out.println("id = "+ct[i].getId()+" |lv= "+map.get(ct[i].getId()));
//		}
//		long time = System.currentTimeMillis() - start;
//		System.out.println("运行耗时= "+time+" 毫秒");
//
//	}
//
//}
