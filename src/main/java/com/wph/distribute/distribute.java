package com.wph.distribute;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class distribute {

	public static double findp(int m,double lambda,double mu){
		double sum = 0;
		double rou = lambda/(m*mu);
		for(int i=0;i<=m;i++){
			sum+=Math.pow(rou, i)/distribute.factorial(i);
//			System.out.println("sum="+sum);
		}
		sum+=Math.pow(rou, m)/(distribute.factorial(m)*(1-rou));
//		System.out.println("sum="+sum+"|rou="+rou+"|this="+Math.pow(rou, m)/(distribute.factorial(m)*(1-rou))
//				+"|pow="+Math.pow(rou, m));
		return 1/sum;
	}
	public static double factorial(int n){
		double sum = 1;
		for(int i=1;i<n;i++){
			sum = sum*i;
		}
		return sum;
	}
	public static int mini(Map<Integer,Double> w){
		double min = 0;
		int mini = 0;
		for(Entry<Integer,Double> wen:w.entrySet()){
			int key = wen.getKey().intValue();
			double val = wen.getValue().doubleValue();
			min = wen.getValue().doubleValue();
			mini = wen.getKey().intValue();
			break;
		}
		for(Entry<Integer,Double> wen:w.entrySet()){
			int key = wen.getKey().intValue();
			double val = wen.getValue().doubleValue();
			if(val<min){
				min = val;
				mini = key;
			}
		}
		return mini;
	}
	public static long findmintime(long[] time){
		long min = time[0];
		for(int i=1;i<time.length;i++){
			if(time[i]<min) min=time[i];
		}
		return min;
	}
	public static long findmaxtime(long[] time){
		long max = time[0];
		for(int i=1;i<time.length;i++){
			if(time[i]>max) max=time[i];
		}
		return max;
	}
//	public static Map<Integer,List<Waitserviceonline>>  csidPerHour(Waitonline[] wo,int avgt,int cust){
//		//输入：顾客、平均服务时间avgt、顾客认为的较合理排队时间cust
//		//输出：<时间段，客服列表>
//		Map<Integer,Integer> timewd = findmPerHour(wo,avgt,cust);
//		//<时间段,该时间段下的窗口数>
//		Map<Integer,List<Waitserviceonline>> timecs = new HashMap<Integer,List<Waitserviceonline>>();
//		//<时间段，客服列表>
//		for(int i=1;i<=12;i++){
//			int m = 0;
//			if(timewd.get(i)!=null)
//			m=timewd.get(i).intValue();
////			System.out.println(i+" "+m);
//			List<Waitserviceonline> l = new ArrayList<>();
//			int summ = 0;
//			for(int j=0;j<wso.length;j++){
//				summ+=wso[j].getCsWindowtotal();
//				l.add(wso[j]);
//				if(summ>=m) break;
//			}
//			timecs.put(i, l);
//		}
//		return timecs;
//	}
	
	public static Map<Integer,Integer> findmPerHour(Waitonline[] wo,int avgt,int cust){
		//输入:顾客、平均服务时间avgt、顾客认为的较合理排队时间cust
		//输出：<时间段,该时间段下的窗口数>
		Map<Integer,List<Waitonline>> timect = new HashMap<Integer,List<Waitonline>>();
		//<时间段,该时间段下等待排队顾客列表>时间段（1,2,3……12）=（1-2,3-4,5-6……23-24）
		//(1-2 = 0:00~1:59 ，3-4=2:00~3:59……23-24=22:00~23:59)
		for(int i=1;i<=12;i++){
			List<Waitonline> l = new ArrayList<>();
			for(int j=0;j<wo.length;j++){
				int hour = ((int) (wo[j].getBegintime().getTime()/1000/3600+8)%24);//8为时区，东八区为+8
				hour = hour/2+1;
				
				if(hour == i){
					l.add(wo[j]);
				}
				
			}
			timect.put(i, l);
		}
		for(int i=1;i<=12;i++){
			System.out.println("时间="+i+"|人数="+timect.get(i).size());
		}
		Map<Integer,Integer> timewd = new HashMap<Integer,Integer>();
		//<时间段,该时间段下的窗口数
		for(int i=1;i<=12;i++){
			List<Waitonline> perl = timect.get(i);
			Waitonline[] percts = perl.toArray(new Waitonline[perl.size()]);
			System.out.println("时间段："+i);
			int m = findm(percts,avgt,cust);
			timewd.put(i, m);
		}
		return timewd;
	}

	public static int findm(Waitonline[] wo,int avgt,int cust){
		//输入：排队中顾客、平均服务时间avgt、顾客认为的较合理排队时间cust、最大窗口数
		//输出：窗口数
		

		double mu = 1/(avgt*1.0);

		double lambda = (wo.length*1.0)/7200;
		System.out.println("lambda="+lambda+"|mu="+mu+"|cust="+(cust*1.0));
		Map<Integer,Double> w = new HashMap<Integer,Double>();
		//<窗口数,期望值>
	
			int m=1;
		while(true){
			double rou = lambda/(m*mu);	
			if(rou>1){
				m++;
				continue;
			} 
			double p = findp(m,lambda,mu);
			double wm = Math.pow(m*rou, m)*rou/(distribute.factorial(m)*(1-rou)*(1-rou)*lambda)*p;
//				System.out.println("m="+m+"|w="+wm+"|lambda="+lambda+"|rou="+rou+"|p="+p);
			
			w.put(m, wm);
//			System.out.println("m="+m+"|w="+wm+"|lambda="+lambda+"|rou="+rou+"|p="+p);
			
			if(wm<(cust*1.0)) {
				System.out.println("m="+m+"|w="+wm+"|lambda="+lambda+"|rou="+rou+"|p="+p);
				return m;
			}
			m++;
		}
	}
}
