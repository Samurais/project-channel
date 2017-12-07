package com.wph.markov;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.wph.model.CustomerWaitModel;

public class SmartList {
	public static double findMax(double[] arr){
		 //Ѱ�����ֵ
	      double max=arr[0];
	      for(int i=1;i<arr.length;i++){
	          if(arr[i]>max){
	             max=arr[i];
	          }
	      }
	      return max;
	  }
	
	public long r(CustomerWaitModel ct,int x,double ctLevelMax){
		//�����ȼ��ķ���غ������������ȼ���Ӧ��ԭʼӦ�ȴ�ʱ��
		//��ʽ��r= x*(���ȼ����ֵ+1-��ǰ���ȼ�)��xΪ������
		//����غ������Ƿǳ���Ҫ��Ӧ�Է������Ϊ��
		return (long) (x*(ctLevelMax+1-ct.getCtLevel()));
	}
	public long haveWaited(CustomerWaitModel ct)
	{
		long time = ct.getBeginWaited().getTime();
		long nowTime = new Date().getTime();
		return (nowTime-time)/1000;
	}
	public long shouldWaited(CustomerWaitModel ct,int x,double ctLevelMax){
		return r(ct,x,ctLevelMax)-haveWaited(ct);
	}
	
	public int findX(CustomerWaitModel[] cts,long averageServiceTime){
		int length = cts.length;
		long[] sw = new long[length];//sw=showldWaited ��Ӧ�ȴ�ʱ��
		long[] dltsw = new long[length-1];//Ӧ�ȴ�ʱ���
		double[] ctlv = new double[length];
		for(int i=0;i<length;i++){
			ctlv[i] = cts[i].getCtLevel();
		}
		boolean flag = true;
		double ctLevelMax = SmartList.findMax(ctlv);
		System.out.println("lvmax="+ctLevelMax);
		int x = 1;
		List<Long> sum = new ArrayList<>();
		List<Integer> loc = new ArrayList<Integer>();
		for(;x<1000;x++){
			//x�Ӿ�����ֵ��ö��
//			System.out.println("x="+x);
			long Rsum=0;
			for(int i=0;i<length;i++){
				sw[i] = shouldWaited(cts[i],x,ctLevelMax);
				if(sw[i] < 0){
					flag = false;
//					System.out.println("xiaoyu0");
					break;
				}
			}
			if(!flag){
//				System.out.println("flag=false");
				flag = true;
				continue;
			}
			Arrays.sort(sw);
			for(int i=0;i<length-1;i++){
				dltsw[i] = sw[i+1]-sw[i];
			}
			for(int i=0;i<dltsw.length;i++){
				Rsum+= Math.abs(dltsw[i]-averageServiceTime);
			}
			sum.add(Rsum);
			loc.add(x);
			System.out.println("x = "+x+"|sum= "+Rsum);
		}
		help[] hp = new help[sum.size()];
		for(int i=0;i<sum.size();i++){
			hp[i] = new help();
			hp[i].setSum(sum.get(i));
			hp[i].setX(loc.get(i));
		}
		
		int minx = help.findMinLocation(hp);
		return minx;
	}
	
	public void getOrder(CustomerWaitModel[] cts,long averageServiceTime){
		int x = findX(cts,averageServiceTime);
		System.out.println("x= "+x);
		int length = cts.length;
		long[] sw = new long[length];//sw=showldWaited ��Ӧ�ȴ�ʱ��
		double[] ctlv = new double[length];
		int[] order = new int[length];
		for(int i=0;i<length;i++){
			ctlv[i] = cts[i].getCtLevel();
		}
		double ctLevelMax = SmartList.findMax(ctlv);
		
		for(int i = 0;i<length;i++){
			sw[i] = shouldWaited(cts[i],x,ctLevelMax);
			cts[i].setRealtime(new Timestamp(sw[i]*1000
					+cts[i].getBeginWaited().getTime()+this.haveWaited(cts[i])*1000));
			System.out.println("i = "+i+
					"|r= "+sw[i]*1000+"|have= "+haveWaited(cts[i])*1000
					+"|begin= "+cts[i].getBeginWaited().getTime());
//			cts[i].setRealtime(cts[i].getBeginWaited()+new Timestamp(new Date().getTime()););
			//����ʱ��=Ӧ�ȴ���ʱ��+�ѵȴ���ʱ��+��ʼʱ��

			/*System.out.println("i = "+i+
					"|r= "+r(cts[i],x,ctLevelMax)+"|have= "+haveWaited(cts[i])
					+"|should= "+sw[i]
					+"|lv= "+cts[i].getCtLevel());*/
		}

	}

}
