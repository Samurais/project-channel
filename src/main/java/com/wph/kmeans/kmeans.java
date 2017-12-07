package com.wph.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wph.model.CustomerrfmModel;

public class kmeans {
	public Map<Integer,Double> kmeans(List<CustomerrfmModel> c,int k)
	{//k-means�㷨
		//����ͻ���Ϣ��kֵ
		//����ͻ���level�ȼ�
		Map<Integer,Double> map = new HashMap<Integer,Double>();
		Map<CustomerrfmModel,Integer> ctmap = new HashMap<CustomerrfmModel,Integer>();
		int length = c.size();
		location[] l = new location[length];
		Rfm[] r = RfmService.customerChangeRfmAndSetAndCal(c);
		for(int i=0;i<length;i++){
			
			l[i] = new location();
			l[i].set(r[i]);
		}
		
		int flag = 1;	//���
		List<location> seeds = initSeed(l,k);//���ӵ�
		while(flag == 1)
		{//ÿ���ظ�ʱ����Ⱥ��Ҫ���г�ʼ���������ӵ�û���ƶ��������
			location[] group = new location[k];//��Ⱥ,��K����ʼ�ľ������ľ���K����Ⱥ
			for(int i=0;i<k;i++){
				group[i] = new location();
				group[i].setRfm(new Rfm());
			}
			int[] sum = new int[k];//ÿ����Ⱥ�ĵ���Ŀ
			for(int i=0;i<length;i++)
			{//�����е�����K�����ӵ�ľ���
				//System.out.println(i);
				location minSeed =  l[i].mindis((location[])seeds
						.toArray(new location[seeds.size()]));
				int minId=minSeed.getId();
				//�����µ�ӽ���ʱ�������µ�ID������ʾ������Ⱥ��
				//��sum+1����Ⱥ��R��F��MֵҲ����
				l[i].setId(minId);

				ctmap.put(c.get(i), minId);
				
				
				sum[minId]++;
				group[minId].getRfm().setR((group[minId].getRfm().getR()+l[i].getRfm().getR()));
				group[minId].getRfm().setF((group[minId].getRfm().getF()+l[i].getRfm().getF()));
				group[minId].getRfm().setM((group[minId].getRfm().getM()+l[i].getRfm().getM()));
			}

			flag = 0;
			for(int i=0;i<k;i++)
			{//�����Ⱥ����
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
	{//��ʼ���ӵ�
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
		    {//�������ֵD(x),�������µ����ӵ�
		    	location usel = l[i].mindis((location [])seeds
		    			.toArray(new location[seeds.size()]));//������
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
