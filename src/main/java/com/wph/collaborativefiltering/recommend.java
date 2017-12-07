package com.wph.collaborativefiltering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class recommend {

	public static double getGoodsSimilar(Map<Integer, Integer> pm1, Map<Integer, Integer> pm2) {
		//pm = <商品ID,是否购买>（是否购买：购买=1，没有=0）
		//pm1 = 受推荐客户
		//pm2 = 被参照客户
        int n = 0;// 数量n
        int sxy = 0;// Σxy=x1*y1+x2*y2+....xn*yn
        int sx = 0;// Σx=x1+x2+....xn
        int sy = 0;// Σy=y1+y2+...yn
        int sx2 = 0;// Σx2=(x1)2+(x2)2+....(xn)2
        int sy2 = 0;// Σy2=(y1)2+(y2)2+....(yn)2
        
//        System.out.println(gm1.size());
        for (Entry<Integer, Integer> pme : pm1.entrySet()) {
        	Integer key = pme.getKey();
            Integer x = pme.getValue();
            Integer y = pm2.get(key);
            System.out.println(" key="+key+" x="+x+" y="+y);
            if (x != null && y != null) {
                n++;
                sxy += x * y;
                sx += x;
                sy += y;
                sx2 += Math.pow(x, 2);
                sy2 += Math.pow(y, 2);
            }
        }
        
        // p=(Σxy-Σx*Σy/n)/Math.sqrt((Σx2-(Σx)2/n)(Σy2-(Σy)2/n));
        double sd = sxy - sx * sy *1.0/ n;
        double sm = Math.sqrt((sx2 - Math.pow(sx, 2) / n) * (sy2 - Math.pow(sy, 2) / n));
        System.out.println(" n="+n+" sxy="+sxy+
        		" sx="+sx+" sy="+sy+" sx2="+sx2+" sy2="+sy2
        		+" sd="+sd+" sm="+sm);
        if(sm == 0){
        	if(sy == 0||sx == 0)
        		return 0;
        	else
        		return 1;
        }
        else return (sd/sm+1)/2;
//        return Math.abs(sm == 0 ? 1 : sd / sm);
    }
	
	 public static Integer getRecommend(Map<Integer, Map<Integer, Integer>> simUserObjMap,
	            Integer ct) {
		 
		    Map<Integer, Double> simUserSimMap = new HashMap<Integer, Double>(); 
		    //simUserSimMap<被参照的客户ID,相似度>
	        System.out.println("皮尔逊相关系数:");

	        for (Entry<Integer, Map<Integer, Integer>> userPerfEn : simUserObjMap.entrySet()) {
	            Integer userName = userPerfEn.getKey();
	            System.out.println("userName="+userName);
	            if (!userName.equals(ct)) {
	            	System.out.println("++" + simUserObjMap.get(ct));
	            	System.out.println("++" +  userPerfEn.getValue());
	                double sim = recommend.getGoodsSimilar(simUserObjMap.get(ct), userPerfEn.getValue());
	                System.out.println(ct+"    与" + userName + "之间的相关系数:" + sim);
	                simUserSimMap.put(userName, sim);
	            }
	        }
	        
	        Map<Integer, Double> objScoreMap = new HashMap<Integer, Double>();
	        List<Integer> unbuylist = new ArrayList<>();
	        for (Entry<Integer, Map<Integer, Integer>> simUserEn : simUserObjMap.entrySet()){
	        	Integer userid = simUserEn.getKey();
	        	if(!userid.equals(ct)) continue;
	        	for (Entry<Integer, Integer> simObjEn : simUserEn.getValue().entrySet()){
	        		if(simObjEn.getValue()==0){
	        			unbuylist.add(simObjEn.getKey());
	        		}
	        	}
	        }
	        for (Entry<Integer, Map<Integer, Integer>> simUserEn : simUserObjMap.entrySet()) {
	        	Integer userid = simUserEn.getKey();
	        	if(!userid.equals(ct)){
	        		//不是受推荐客户 
	        		double sim = simUserSimMap.get(userid);
		            for (Entry<Integer, Integer> simObjEn : simUserEn.getValue().entrySet()) {
		            	if(unbuylist.contains(simObjEn.getKey())){
		            		//不是受推荐客户已买商品
		            		double objScore = sim * simObjEn.getValue();
//		            		System.out.println("gsid="+simObjEn.getKey()+" |score="+objScore);
			                Integer objid = simObjEn.getKey();
			                if (objScoreMap.get(objid) == null) {
			                    objScoreMap.put(objid, objScore);
			                } else {
			                    double totalScore = objScoreMap.get(objid);
			                    objScoreMap.put(objid, totalScore + objScore);
			                }
		            	}    
		            }
	        	}
	            
	        }
	        List<Entry<Integer, Double>> enList = new ArrayList<Entry<Integer, Double>>(objScoreMap.entrySet());
	        Collections.sort(enList, new Comparator<Map.Entry<Integer, Double>>() {
	            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
	                Double a = o1.getValue() - o2.getValue();
	                if (a == 0) {
	                    return 0;
	                } else if (a > 0) {
	                    return 1;
	                } else {
	                    return -1;
	                }
	            }
	        });
	        System.out.println("enList.size()="+enList.size());
	        if(enList.size()==0){
	        	System.out.println("没有推荐物品");
	        	return -1;
	        }
	        return enList.get(enList.size() - 1).getKey();
	    }
}
	