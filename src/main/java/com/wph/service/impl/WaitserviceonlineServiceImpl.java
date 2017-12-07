package com.wph.service.impl;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.wph.entities.Customerservice;
import com.wph.entities.Waitserviceonline;
import com.wph.service.WaitserviceonlineService;

@Service("waitserviceonlineService")
public class WaitserviceonlineServiceImpl extends BaseServiceImpl<Waitserviceonline>
		implements WaitserviceonlineService {

	@Override
	public Boolean isFree() {
		System.out.println("waitserviceonlineService:isFree");
		String hql = "select count(*) from Waitserviceonline";
		Long count = (Long) getSession().createQuery(hql).uniqueResult();
		return count > 0;
	}

	@Override
	public Boolean isFree(Integer companyid) {
		System.out.println("waitserviceonlineService:isFree");
		String hql = "select count(*) from Waitserviceonline w where w.csId in"
				+ " (select c.csId from Customerservice c where c.company.cpId =:companyid)";
		Long count = (Long) getSession().createQuery(hql).setInteger("companyid", companyid).uniqueResult();
		return count > 0;
	}

	@Override
	public synchronized Customerservice popCustomerService() {
		System.out.println("popCustomerService");
		// ��ÿͷ��ȴ�������еģ��ͷ�id�Ϳͷ�ʣ�ര��
		String sql = "select * from waitserviceonline w where w.begintime=(select min(o.begintime) from waitserviceonline o)";
		Waitserviceonline role = (Waitserviceonline) getSession().createSQLQuery(sql).addEntity(Waitserviceonline.class)
				.uniqueResult();
		System.out.println("�˹��ͷ�" + role.getCsId() + "�������");
		// ��ÿͷ���id
		Integer csId = role.getCsId();
		// ��ÿͷ���ʣ�ര������
		Integer windowRemain = role.getCsWindowremain();
		// ���ֻ��һ������
		if (windowRemain == 1) {
			// ɾ���ȴ����н�ɫ
			delete(role);
		} else {
			// ���򴰿�-1�����������ÿ�ʼ�ȴ�ʱ��
			role.setCsWindowremain(windowRemain - 1);
			role.setBegintime(new Timestamp(System.currentTimeMillis()));
			update(role);
		}
		System.out.println("���ؿͷ�����");
		// ���ؿͷ�����
		String hql = "from Customerservice c where c.csId = :csId";
		return (Customerservice) getSession().createQuery(hql).setInteger("csId", csId).uniqueResult();
	}

	@Override
	public Integer popCustomerService(Integer companyid) {
		System.out.println("popCustomerService");
		// ��ÿͷ��ȴ�������еģ��ͷ�id�Ϳͷ�ʣ�ര��
		String sql = "select * from waitserviceonline w where w.begintime=(select min(o.begintime) from waitserviceonline o) and "
				+ "w.cs_id in(select c.cs_id from customerservice c where c.company_id =:companyid)";
		Waitserviceonline role = (Waitserviceonline) getSession().createSQLQuery(sql).addEntity(Waitserviceonline.class)
				.setInteger("companyid", companyid).uniqueResult();
		System.out.println("�˹��ͷ�" + role.getCsId() + "�������");
		// ��ÿͷ���id
		Integer csId = role.getCsId();
		// ��ÿͷ���ʣ�ര������
		Integer windowRemain = role.getCsWindowremain();
		// ���ֻ��һ������
		if (windowRemain == 1) {
			// ɾ���ȴ����н�ɫ
			delete(role);
		} else {
			// ���򴰿�-1�����������ÿ�ʼ�ȴ�ʱ��
			role.setCsWindowremain(windowRemain - 1);
			role.setBegintime(new Timestamp(System.currentTimeMillis()));
			update(role);
		}
		System.out.println("���ؿͷ�����");
		// ���ؿͷ�����
//		String hql = "from Customerservice c where c.csId = :csId";
		return csId;
	}

	@Override
	public void pushCustomerService(Customerservice customerservice) {
		System.out.println("pushCustomerService");
		Integer csId = customerservice.getCsId();
		Object serviceob = getSession().get(Customerservice.class, csId);
		if(serviceob==null){return;}
		customerservice = (Customerservice)serviceob;
		System.out.println(csId);
		String sql = "select count(*) from waiserviceonline w where ";
		String hql = "select count(*) from Waitserviceonline w where w.customerservice.csId = :csId";
		Long count = (Long) getSession().createQuery(hql).setInteger("csId", csId).uniqueResult();
		if (count == 0) {
			// ����ͷ�������,�򽫿ͷ��������
			Waitserviceonline waitserviceonline = new Waitserviceonline();
			waitserviceonline.setCsId(customerservice.getCsId());
			waitserviceonline.setCsWindowtotal(customerservice.getCsWindow());
			waitserviceonline.setCsWindowremain(customerservice.getCsWindow());
			waitserviceonline.setBegintime(new Timestamp(System.currentTimeMillis()));
			save(waitserviceonline);
		} else {
			// ����ͷ�����,�ͷ����ڴ���-1
			Waitserviceonline role = get(customerservice.getCsId());
			// ��ÿͷ���ʣ�ര������
			Integer windowRemain = role.getCsWindowremain();
			// ����+1�����������ÿ�ʼ�ȴ�ʱ��
			role.setCsWindowremain(windowRemain + 1);
			update(role);
		}
	}

	@Override
	public void servicelogout(Integer id) {
		Waitserviceonline wait = get(id);
		if (wait != null) {
			delete(wait);
		}
	}

	@Override
	public void serviceConnectWebSocket(Customerservice customerservice) {
		System.out.println("pushCustomerService");
		Integer csId = customerservice.getCsId();
		Object serviceob = getSession().get(Customerservice.class, csId);
		if(serviceob==null){return;}
		customerservice = (Customerservice)serviceob;
		System.out.println(csId);
		String sql = "select count(*) from waiserviceonline w where ";
		String hql = "select count(*) from Waitserviceonline w where w.customerservice.csId = :csId";
		Long count = (Long) getSession().createQuery(hql).setInteger("csId", csId).uniqueResult();
		if (count == 0) {
			// ����ͷ�������,�򽫿ͷ��������
			Waitserviceonline waitserviceonline = new Waitserviceonline();
			waitserviceonline.setCsId(customerservice.getCsId());
			waitserviceonline.setCsWindowtotal(customerservice.getCsWindow());
			waitserviceonline.setCsWindowremain(customerservice.getCsWindow());
			waitserviceonline.setBegintime(new Timestamp(System.currentTimeMillis()));
			save(waitserviceonline);
		} else {
			// ����ͷ�����,�ͷ����ڴ���-1
			Waitserviceonline role = get(customerservice.getCsId());
			role.setCsWindowtotal(customerservice.getCsWindow());
			role.setCsWindowremain(customerservice.getCsWindow());
			role.setBegintime(new Timestamp(System.currentTimeMillis()));
			update(role);
		}
		
	}

}
