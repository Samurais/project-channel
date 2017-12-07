package com.wph.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Service;

import com.wph.model.ServiceActive;
import com.wph.service.ServiceActiveService;

@Service("serviceActiveService")
public class ServiceActiveServiceImpl extends BaseServiceImpl<ServiceActive> implements ServiceActiveService {

	@Override
	public ServiceActive getServiceActive(Integer serviceid) {
		ServiceActive serviceActive = new ServiceActive();
		//����serviceActiveId
		serviceActive.setId(serviceid);
		//�鿴�Ƿ����ڻỰ
		String sql1 = "select count(*) from chatonline c where c.customerservice_id = :serviceid";
		BigInteger chatonlinecount =(BigInteger) getSession().createSQLQuery(sql1).setInteger("serviceid", serviceid).uniqueResult();
		//�鿴�Ƿ����ŶӴ���
		String sql2 = "select count(*) from waitserviceonline w where w.cs_id = :serviceid";
		BigInteger waitserviceonlinecount = (BigInteger) getSession().createSQLQuery(sql2).setInteger("serviceid", serviceid).uniqueResult();
		
		if(chatonlinecount.intValue() >0){
			//������ڻỰ,�õ��Ự�ͻ���ID
			String sql3 = "select customer_id from chatonline c where c.customerservice_id = :serviceid";
			List<Integer> currentchatids =getSession().createSQLQuery(sql3).addScalar("customer_id", StandardBasicTypes.INTEGER).setInteger("serviceid", serviceid).list();
			serviceActive.setCurrentservice(currentchatids.get(0));
			serviceActive.setIsonline(true);
		}else{
			if(waitserviceonlinecount.intValue()>0){
				serviceActive.setIsonline(true);
				System.currentTimeMillis();
			}else{
				serviceActive.setIsonline(false);
			}
		
		}
		
		//�õ�mysql�ķ���,��ȡ����Ự����,�м���������
		String sql4 = "select count(*) from conversation c where to_days(c.begintime) = to_days(now()) and c.costomerservice_id = :serviceid";
		BigInteger chatcount =(BigInteger) getSession().createSQLQuery(sql4).setInteger("serviceid", serviceid).uniqueResult();
		serviceActive.setHaveservice(chatcount.intValue());
		
		//��ȡ��½ʱ��
		String sql5 = "select logintime from customerserviceloginrecord c where c.costomerservice_id = :serviceid";
		List<Object> obs = getSession().createSQLQuery(sql5).setInteger("serviceid", serviceid).list();
		System.out.println(obs);
		if(obs.size()>0){
			serviceActive.setFirstlandtime(obs.get(0).toString());
		}
		System.out.println("serviceActive" + serviceActive);
		return serviceActive;
	}

	
}
