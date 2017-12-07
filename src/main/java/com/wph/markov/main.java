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
		
		//�㷨���룺���ȼ����ֵ��ƽ������ʱ�䣨�����n�����ڣ�ƽ������ʱ��=����ƽ������ʱ��/n��
		//�ͻ��Ŀ�ʼ�ȴ�ʱ�䣨���ڸ�ʽ�����ͻ������ȼ�
		//�㷨������ͻ��ĵȴ�����.
		//ÿ�����¿ͻ��ӽ�������������һ�θ��㷨�����޸Ĺ˿͵�˳��
		
		
		
		long start=System.currentTimeMillis();
		List<CustomerWaitModel> cts = new ArrayList<>();
		int length = 100;
		Random r = new Random();
		long averageServiceTime = 60;//�趨ƽ������ʱ��
		for(int i=0;i<length;i++){
			CustomerWaitModel ct = new CustomerWaitModel();
			ct.setBeginWaited(new Timestamp((new Date().getTime()/1000-r.nextInt(300))*1000));//�趨��ʼʱ��
			ct.setCtLevel(1.0*r.nextInt(11));//�趨���ȼ�(����10λ���,��Ȼ���ֵ���㷨������������ֶ�����)
			//�¹˿����ȼ�Ϊ0
			cts.add(ct);//�趨�˿���Ϣ
		}
		SmartList l = new SmartList();
		l.getOrder(cts.toArray(new CustomerWaitModel[cts.size()]), averageServiceTime);
		//�����Ŷ��㷨������Ϊ�ͻ����飨��Ҫ�пͻ���ʼ�ȴ�ʱ�䡢�ͻ����ȼ�����ƽ������ʱ�䡢
		//�ͻ����ȼ����ֵ��
		
		for(int i=0;i<cts.size();i++){
			
			System.out.println("i = "+i+"|ct = "+cts.get(i));
		}
		
		
		long runTime = System.currentTimeMillis() - start;
		System.out.println("���к�ʱ= "+runTime+" ����");

	}

}
