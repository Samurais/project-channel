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
////������Ʒ���룺�˿͵����һ�ι����ʱ�䡢
////���¹�����������¹�����������¹����ܶ���¹����ܶrfmֵ
//	
////kmeans�㷨���룺�˿͵����һ�ι����ʱ�䡢
////���¹�����������¹�����������¹����ܶ���¹����ܶkֵ
////��ȺID����0��ʼ��k-1����
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		long start=System.currentTimeMillis();
//		
//		//����˿�����
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
//		//�����������
//		
//		
//		
//		int k = 10;//kֵ�������ù������ù����Ӵ�����ʱ�䡣
//
//		if(ct.length<=0){
//			//ע�����ݵ�Ϊ0����������ݿ���û�����ݣ�
//			return;
//		}
//		if(k>ct.length)
//		{//kֵ���ܱ�����������
//			System.out.println("Kֵ����");
//			return;
//		}
//		if(k<=0){
//			//kֵ����С��0
//			System.out.println("Kֵ��С");
//			return;
//		}
//
//		kmeans ks = new kmeans();
//		Map<Integer,Double> map = ks.kmeans(ct,k); //K-Means�㷨������ct��ʾcustomer���飬k��ʾ�����k
//
//	
//		for(int i=0;i<ct.length;i++){
//			System.out.println("id = "+ct[i].getId()+" |lv= "+map.get(ct[i].getId()));
//		}
//		long time = System.currentTimeMillis() - start;
//		System.out.println("���к�ʱ= "+time+" ����");
//
//	}
//
//}
