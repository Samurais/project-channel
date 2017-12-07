package com.wph.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wph.model.CustomerrfmModel;

public class kmeans {
	public Map<Integer,Double> kmeans(List<CustomerrfmModel> c,int k)
	{//k-means算法
		//输入客户信息和k值
		//输出客户的level等级
		Map<Integer,Double> map = new HashMap<Integer,Double>();
		Map<CustomerrfmModel,Integer> ctmap = new HashMap<CustomerrfmModel,Integer>();
		int length = c.size();
		location[] l = new location[length];
		Rfm[] r = RfmService.customerChangeRfmAndSetAndCal(c);
		for(int i=0;i<length;i++){
			
			l[i] = new location();
			l[i].set(r[i]);
		}
		
		int flag = 1;	//标记
		List<location> seeds = initSeed(l,k);//种子点
		while(flag == 1)
		{//每次重复时，点群都要进行初始化，当种子点没有移动，则结束
			location[] group = new location[k];//点群,有K个初始的聚类中心就有K个点群
			for(int i=0;i<k;i++){
				group[i] = new location();
				group[i].setRfm(new Rfm());
			}
			int[] sum = new int[k];//每个点群的点数目
			for(int i=0;i<length;i++)
			{//对所有点求到这K个种子点的距离
				//System.out.println(i);
				location minSeed =  l[i].mindis((location[])seeds
						.toArray(new location[seeds.size()]));
				int minId=minSeed.getId();
				//当有新点加进来时，赋给新点ID用来表示所属点群，
				//再sum+1，点群中R、F、M值也增加
				l[i].setId(minId);

				ctmap.put(c.get(i), minId);
				
				
				sum[minId]++;
				group[minId].getRfm().setR((group[minId].getRfm().getR()+l[i].getRfm().getR()));
				group[minId].getRfm().setF((group[minId].getRfm().getF()+l[i].getRfm().getF()));
				group[minId].getRfm().setM((group[minId].getRfm().getM()+l[i].getRfm().getM()));
			}

			flag = 0;
			for(int i=0;i<k;i++)
			{//计算点群中心
				if(sum[i]==0) continue;

				group[i].getRfm().setR(group[i].getRfm().getR()/sum[i]);
				group[i].getRfm().setF(group[i].getRfm().getF()/sum[i]);
				group[i].getRfm().setM(group[i].getRfm().getM()/sum[i]);
				if(seeds.get(i).getRfm().getR()!=group[i].getRfm().getR()||
						seeds.get(i).getRfm().getF()!=group[i].getRfm().getF()||
								seeds.get(i).getRfm().getM()!=group[i].getRfm().getM()
						){			
					flag = 1;
				}

				seeds.get(i).getRfm().setR(group[i].getRfm().getR());
				seeds.get(i).getRfm().setF(group[i].getRfm().getF());
				seeds.get(i).getRfm().setM(group[i].getRfm().getM());
	
			}
			
		}
		Rfm[] seedsRfm = new Rfm[k];
		for(int i=0;i<k;i++){
			seedsRfm[i] = seeds.get(i).getRfm();
		}
		map = RfmService.setCtLevel(c,k,seedsRfm,ctmap);
		return map;
	}

	public List<location> initSeed(location[] l,int k)
	{//初始种子点
		int length = l.length;
		double[] distence = new double[length];
		List<location> seeds = new ArrayList<>();
		l[0].setId(seeds.size());
		seeds.add(l[0]);
		while(seeds.size()<k)
		{
			double maxdis = 0;
			int maxi = 0;
		    for(int i=0;i<length;i++)
		    {//计算最大值D(x),用来当新的种子点
		    	location usel = l[i].mindis((location [])seeds
		    			.toArray(new location[seeds.size()]));//过度用
		    	distence[i] = usel.distance(l[i]);
		    	if(distence[i]>maxdis)
		    	{
		    		maxdis = distence[i];
		    		maxi = i;
		    	}
		    }		    
		    l[maxi].setId(seeds.size());
		    seeds.add(l[maxi]);
		}
		
		return seeds;
		
	}

}
