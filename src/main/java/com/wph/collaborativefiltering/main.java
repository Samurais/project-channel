package com.wph.collaborativefiltering;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class main {

	public static void main(String[] args) {
		Map<Integer, Map<Integer, Integer>> userPerfMap = new HashMap<Integer, Map<Integer, Integer>>();
        Map<Integer, Integer> pref1 = new HashMap<Integer, Integer>();
        pref1.put(0, 0);
        pref1.put(1, 0);
        pref1.put(2, 0);
        pref1.put(3, 0);
        pref1.put(4, 0);
        userPerfMap.put(0, pref1);
        Map<Integer, Integer> pref2 = new HashMap<Integer, Integer>();
        pref2.put(0, 1);
        pref2.put(1, 0);
        pref2.put(2, 1);
        pref2.put(3, 0);
        pref2.put(4, 1);

        userPerfMap.put(1, pref2);
        Map<Integer, Integer> pref3 = new HashMap<Integer, Integer>();
        pref3.put(0, 0);
        pref3.put(1, 0);
        pref3.put(2, 0);
        pref3.put(3, 0);
        pref3.put(4, 0);

        userPerfMap.put(2, pref3);
        Map<Integer, Integer> pref4 = new HashMap<Integer, Integer>();
        pref4.put(0, 0);
        pref4.put(1, 0);
        pref4.put(2, 0);
        pref4.put(3, 0);
        pref4.put(4, 0);

        userPerfMap.put(3, pref4);
        Map<Integer, Integer> pref5 = new HashMap<Integer, Integer>();
        pref5.put(0, 1);
        pref5.put(1, 0);
        pref5.put(2, 1);
        pref5.put(3, 1);
        pref5.put(4, 1);

        userPerfMap.put(4, pref5);
        
//        Map<Integer, Integer> pref6 = new HashMap<Integer, Integer>();
//        pref6.put(0, 0);
//        pref6.put(1, 0);
//        pref6.put(2, 0);
//        pref6.put(3, 0);
//        pref6.put(4, 0);
//
//        userPerfMap.put(5, pref6);
//        Map<Integer, Integer> pref7 = new HashMap<Integer, Integer>();
//        pref7.put(0, 0);
//        pref7.put(1, 0);
//        pref7.put(2, 0);
//        pref7.put(3, 0);
//        pref7.put(4, 0);
//
//        userPerfMap.put(6, pref7);
//        Map<Integer, Integer> pref8 = new HashMap<Integer, Integer>();
//        pref8.put(0, 0);
//        pref8.put(1, 0);
//        pref8.put(2, 0);
//        pref8.put(3, 0);
//        pref8.put(4, 0);
//
//        userPerfMap.put(7, pref8);
//        Map<Integer, Integer> pref9 = new HashMap<Integer, Integer>();
//        pref9.put(0, 0);
//        pref9.put(1, 0);
//        pref9.put(2, 0);
//        pref9.put(3, 0);
//        pref9.put(4, 0);
//
//        userPerfMap.put(8, pref9);
        Customer[] cts = new Customer[70];
        for(int i=0;i<cts.length;i++){
        	cts[i] = new Customer();
        	cts[i].setCtId(i);
        }

       
//		int gsid = recommend.getRecommend(userPerfMap,cts[4]);
//		if(gsid>0){
//			System.out.println("推荐结果: "+gsid);
//		}
		int gsid1 = recommend.getRecommend(userPerfMap,cts[1].getCtId());
		if(gsid1>0){
			System.out.println("推荐结果: "+gsid1);
		}
        
//		Customer[] cts = new Customer[50];
//		Goods[] gs = new Goods[5];
//		//<商品ID,<顾客ID,打分值>>
//		Map<Integer, Map<Integer, Integer>> simGoodsObjMap = new HashMap<Integer, Map<Integer, Integer>>();
//		//<商品1ID,<商品2ID,相似度>>
//		Map<Integer, Map<Integer,Double>> simGoodsSimMap = new HashMap<Integer, Map<Integer,Double>>();
//		Random r = new Random();
//		for(int i=0;i<gs.length;i++){
//			gs[i] = new Goods();
//			gs[i].setGsId(i);
//		}
//		for(int i=0;i<cts.length;i++){
//			cts[i] = new Customer();
//			cts[i].setCtId(i);
//		}
//		Map<Integer,Integer>[] map = new Map[gs.length];
//		for(int i=0;i<gs.length;i++){
//			map[i] = new HashMap<Integer,Integer>();
//			for(int j=0;j<cts.length;j++){
//				if(j!=cts.length-1)
//				map[i].put(cts[j].getCtId(), r.nextInt(10));
//				else{
//					if(i<gs.length*1.0/2)
//						map[i].put(cts[j].getCtId(), r.nextInt(10));
//				}
//					
//			}
//			simGoodsObjMap.put(gs[i].getGsId(), map[i]);
//
//		}
//		Map<Integer,Double>[] simMap = new Map[gs.length*(gs.length-1)+10];
//		int k=0;
//		for(int i=0;i<gs.length;i++){
//			for (Entry<Integer, Map<Integer, Integer>> simGoodsObjEn : simGoodsObjMap.entrySet()) {
//				Integer gsid = simGoodsObjEn.getKey();
//	            if (gs[i].getGsId()!=gsid) {
//	            	simMap[k] = new HashMap<Integer,Double>();
//	                double sim = recommend.getGoodsSimilar(simGoodsObjMap.get(gs[i].getGsId()), simGoodsObjMap.get(gsid));
//	                System.out.println(gs[i].getGsId()+"与" + gsid + "之间的相关系数:" + sim);
//	                simMap[k].put(gsid, sim);
//	                simGoodsSimMap.put(gs[i].getGsId(), simMap[k]);
//	                k++;
//	                System.out.println(gs[i].getGsId()+" "+simGoodsSimMap.get(gs[i].getGsId()));
//	            }
//	        }
//		}
//
//		System.out.println(recommend.getRecommend(simGoodsObjMap, simGoodsSimMap, gs, cts[cts.length-1]));
//		
//
	}

}
