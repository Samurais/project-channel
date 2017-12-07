package com.wph.markov;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.wph.model.CustomerWaitModel;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//算法输入：优先级最大值、平均服务时间（如果有n个窗口，平均服务时间=单个平均服务时间/n）
		//客户的开始等待时间（日期格式）、客户的优先级
		//算法输出：客户的等待次序.
		//每当有新客户加进来，就重新用一次该算法，并修改顾客的顺序
		
		
		
		long start=System.currentTimeMillis();
		List<CustomerWaitModel> cts = new ArrayList<>();
		int length = 100;
		Random r = new Random();
		long averageServiceTime = 60;//设定平均服务时间
		for(int i=0;i<length;i++){
			CustomerWaitModel ct = new CustomerWaitModel();
			ct.setBeginWaited(new Timestamp((new Date().getTime()/1000-r.nextInt(300))*1000));//设定开始时间
			ct.setCtLevel(1.0*r.nextInt(11));//设定优先级(假设10位最高,当然最大值是算法算出来，无需手动输入)
			//新顾客优先级为0
			cts.add(ct);//设定顾客信息
		}
		SmartList l = new SmartList();
		l.getOrder(cts.toArray(new CustomerWaitModel[cts.size()]), averageServiceTime);
		//智能排队算法，参数为客户数组（需要有客户开始等待时间、客户优先级）、平均服务时间、
		//客户优先级最大值。
		
		for(int i=0;i<cts.size();i++){
			
			System.out.println("i = "+i+"|ct = "+cts.get(i));
		}
		
		
		long runTime = System.currentTimeMillis() - start;
		System.out.println("运行耗时= "+runTime+" 毫秒");

	}

}
