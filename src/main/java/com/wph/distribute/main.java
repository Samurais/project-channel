package com.wph.distribute;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Waitonline[] wo = new Waitonline[5000];
		Random r = new Random();
		for (int i = 0; i < wo.length; i++) {
			wo[i] = new Waitonline();
			wo[i].setCustomerId(i);
			wo[i].setBegintime(new Timestamp((new Date().getTime() / 1000 / 3600 + r.nextInt(24)) * 1000 * 3600));
			// System.out.println("�ͻ�id = " +i+"
			// |begintime="+wo[i].getBegintime());
			
		}

		int avgt = 300;// ƽ������ʱ��
		int cust = 500;// �˿���Ϊ����ĵȴ�ʱ��

		// Map<Integer,List<Waitserviceonline>> m= distribute.csidPerHour(wo,
		// wso, avgt, cust, maxwindows);
		Map<Integer, Integer> m = distribute.findmPerHour(wo, avgt, cust);
		for (int i = 1; i <= 12; i++) {
			System.out.println("��ǰʱ��Σ�" + i + " ��Ҫ��������" + m.get(i));
		}
		// for(int i=1;i<=12;i++){
		// if(m.get(i) == null){
		// System.out.println("��ǰʱ��Σ�"+i+" ������ͷ�");
		// }else{
		// System.out.println("��ǰʱ��Σ�"+i+" ��Ҫ��������"+m2.get(i)+" ��Ҫ�ͷ�");
		// for(int j=0;j<m.get(i).size();j++){
		// System.out.print(m.get(i).get(j).getCsId());
		// if(j!=m.get(i).size()-1)
		// System.out.print("��");
		// else System.out.println();
		// }
		// }
		//
		// }

		// System.out.println("m="+m);

	}

}
