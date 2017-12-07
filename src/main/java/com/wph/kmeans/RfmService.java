package com.wph.kmeans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;

import com.wph.model.CustomerrfmModel;

public class RfmService {
	// �����Ǹ������࣬�����������Ծ�̬����ʵ��
	public static double findMax(double[] arr) {
		// Ѱ�����ֵ
		if (arr.length == 0) {
			return 0;
		}
		double max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}

	public static double findMin(double[] arr) {
		// Ѱ����Сֵ
		if (arr.length == 0) {
			return 0;
		}
		double min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		return min;
	}

	private static Integer rfmtimeReference = 1;

	@Value("#{prop.rfmtimeReference}")
	// @Value��ʾȥbeans.xml�ļ�����id="prop"��bean������ͨ��ע��ķ�ʽ��ȡproperties�����ļ��ģ�Ȼ��ȥ��Ӧ�������ļ��ж�ȡkey=filePath��ֵ
	public void setRfmtimeReference(Integer rfmtimeReference) {
		this.rfmtimeReference = rfmtimeReference;
	}
	
	public static Rfm[] customerChangeRfmAndSetAndCal(List<CustomerrfmModel> ct) {
		// �ѹ˿���Ϣת��Ϊrfm��Ϣ�����벢����rfmֵ,����rfmֵ����˿���Ϣ��
		Date nowDate = new Date();
		int length = ct.size();
		double[] r = new double[length];
		double[] f = new double[length];
		double[] m = new double[length];
		Rfm[] rfm = new Rfm[length];
		for (int i = 0; i < length; i++) {
			if (ct.get(i).getLastbuytime() != null) {
				Integer year = new Timestamp(System.currentTimeMillis()).getYear() - rfmtimeReference;
				Timestamp refertime = new Timestamp(year, 1, 1, 0, 0, 0, 0);
				Double time = ct.get(i).getLastbuytime().getTime() * 1.0 / 1000 / 3600 / 24
						- refertime.getTime() * 1.0 / 1000 / 3600 / 24;
				if(time<0){
					r[i] = 0;					
				}else{
					r[i] = time;
				}
				// getTime���ش�1970��1��1�յ����ڵĺ�����
				// ������r��ֵ��������
			} else {
				r[i] = 0;
			}
			if (ct.get(i).getMonthbuytimes() != null && ct.get(i).getLastmonthbuytimes() != null) {
				f[i] = ct.get(i).getMonthbuytimes() + ct.get(i).getLastmonthbuytimes();
			} else {
				if (ct.get(i).getMonthbuytimes() != null) {
					f[i] = ct.get(i).getMonthbuytimes();
				} else if (ct.get(i).getLastmonthbuytimes() != null) {
					f[i] = ct.get(i).getLastmonthbuytimes();
				} else {
					f[i] = 0;
				}
			}
			if (ct.get(i).getMonthbuysum() != null && ct.get(i).getLastmonthbuysum() != null) {
				m[i] = ct.get(i).getMonthbuysum() + ct.get(i).getLastmonthbuysum();
			} else {
				if (ct.get(i).getMonthbuysum() != null) {
					f[i] = ct.get(i).getMonthbuysum();
				} else if (ct.get(i).getLastmonthbuysum() != null) {
					f[i] = ct.get(i).getLastmonthbuysum();
				} else {
					f[i] = 0;
				}
			}
			System.out.println("!re!i= " + i + " r= " + r[i] + " f= " + f[i] + " m= " + m[i]);
		}
		// ��ȡR��F��M�����ֵ����Сֵ
		double maxR = RfmService.findMax(r);
		double maxF = RfmService.findMax(f);
		double maxM = RfmService.findMax(m);
		double minR = RfmService.findMin(r);
		double minF = RfmService.findMin(f);
		double minM = RfmService.findMin(m);
		for (int i = 0; i < length; i++) {
			// ��һ������ y=100/(max-min)*x-100*min/(max-min)(���ֵ��100����Сֵ��0)
			rfm[i] = new Rfm();
			double rr = r[i];
			r[i] = 100 / (maxR - minR) * rr - 100 * minR / (maxR - minR);
			f[i] = 100 / (maxF - minF) * f[i] - 100 * minF / (maxF - minF);
			m[i] = 100 / (maxM - minM) * m[i] - 100 * minM / (maxM - minM);

			rfm[i].setR(r[i]);
			rfm[i].setF(f[i]);
			rfm[i].setM(m[i]);

			System.out.println("i= " + i);
			System.out.println("rfm= " + rfm[i] + "maxR= " + maxR + "minR= " + minR + "rr= " + rr);
		}

		return rfm;
	}

	public static Map<Integer, Double> setCtLevel(List<CustomerrfmModel> cts, int k, Rfm[] rfm,
			Map<CustomerrfmModel, Integer> ctmap) {
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		int length = cts.size();
		Double[] sumRfm = new Double[length];
		List<Double> l = new ArrayList<>();

		for (int i = 0; i < length; i++) {
			double sum = rfm[ctmap.get(cts.get(i))].getR() + rfm[ctmap.get(cts.get(i))].getF()
					+ rfm[ctmap.get(cts.get(i))].getM();
			if (!l.contains(sum)) {
				l.add(sum);

			}
			System.out.println(
					"i= " + i + "|r= " + rfm[ctmap.get(cts.get(i))].getR() + "|f= " + rfm[ctmap.get(cts.get(i))].getF()
							+ "|m= " + rfm[ctmap.get(cts.get(i))].getM() + "|ctr= " + " |sum= " + sum);

		}

		sumRfm = l.toArray(new Double[l.size()]);
		Arrays.sort(sumRfm);
		for (int i = 0; i < length; i++) {
			double sum = rfm[ctmap.get(cts.get(i))].getR() + rfm[ctmap.get(cts.get(i))].getF()
					+ rfm[ctmap.get(cts.get(i))].getM();
			for (int j = 0; j < k; j++) {
				if (sum == sumRfm[j]) {
					map.put(cts.get(i).getId(), j * 1.0);
					break;
				}
			}
		}
		return map;
	}

}
