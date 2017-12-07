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
		// 获得客服等待服务表中的，客服id和客服剩余窗口
		String sql = "select * from waitserviceonline w where w.begintime=(select min(o.begintime) from waitserviceonline o)";
		Waitserviceonline role = (Waitserviceonline) getSession().createSQLQuery(sql).addEntity(Waitserviceonline.class)
				.uniqueResult();
		System.out.println("人工客服" + role.getCsId() + "进入服务");
		// 获得客服的id
		Integer csId = role.getCsId();
		// 获得客服的剩余窗口数量
		Integer windowRemain = role.getCsWindowremain();
		// 如果只有一个窗口
		if (windowRemain == 1) {
			// 删除等待队列角色
			delete(role);
		} else {
			// 否则窗口-1，并重新设置开始等待时间
			role.setCsWindowremain(windowRemain - 1);
			role.setBegintime(new Timestamp(System.currentTimeMillis()));
			update(role);
		}
		System.out.println("返回客服对象");
		// 返回客服对象
		String hql = "from Customerservice c where c.csId = :csId";
		return (Customerservice) getSession().createQuery(hql).setInteger("csId", csId).uniqueResult();
	}

	@Override
	public Integer popCustomerService(Integer companyid) {
		System.out.println("popCustomerService");
		// 获得客服等待服务表中的，客服id和客服剩余窗口
		String sql = "select * from waitserviceonline w where w.begintime=(select min(o.begintime) from waitserviceonline o) and "
				+ "w.cs_id in(select c.cs_id from customerservice c where c.company_id =:companyid)";
		Waitserviceonline role = (Waitserviceonline) getSession().createSQLQuery(sql).addEntity(Waitserviceonline.class)
				.setInteger("companyid", companyid).uniqueResult();
		System.out.println("人工客服" + role.getCsId() + "进入服务");
		// 获得客服的id
		Integer csId = role.getCsId();
		// 获得客服的剩余窗口数量
		Integer windowRemain = role.getCsWindowremain();
		// 如果只有一个窗口
		if (windowRemain == 1) {
			// 删除等待队列角色
			delete(role);
		} else {
			// 否则窗口-1，并重新设置开始等待时间
			role.setCsWindowremain(windowRemain - 1);
			role.setBegintime(new Timestamp(System.currentTimeMillis()));
			update(role);
		}
		System.out.println("返回客服对象");
		// 返回客服对象
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
			// 如果客服不存在,则将客服加入队列
			Waitserviceonline waitserviceonline = new Waitserviceonline();
			waitserviceonline.setCsId(customerservice.getCsId());
			waitserviceonline.setCsWindowtotal(customerservice.getCsWindow());
			waitserviceonline.setCsWindowremain(customerservice.getCsWindow());
			waitserviceonline.setBegintime(new Timestamp(System.currentTimeMillis()));
			save(waitserviceonline);
		} else {
			// 如果客服存在,客服存在窗口-1
			Waitserviceonline role = get(customerservice.getCsId());
			// 获得客服的剩余窗口数量
			Integer windowRemain = role.getCsWindowremain();
			// 窗口+1，并重新设置开始等待时间
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
			// 如果客服不存在,则将客服加入队列
			Waitserviceonline waitserviceonline = new Waitserviceonline();
			waitserviceonline.setCsId(customerservice.getCsId());
			waitserviceonline.setCsWindowtotal(customerservice.getCsWindow());
			waitserviceonline.setCsWindowremain(customerservice.getCsWindow());
			waitserviceonline.setBegintime(new Timestamp(System.currentTimeMillis()));
			save(waitserviceonline);
		} else {
			// 如果客服存在,客服存在窗口-1
			Waitserviceonline role = get(customerservice.getCsId());
			role.setCsWindowtotal(customerservice.getCsWindow());
			role.setCsWindowremain(customerservice.getCsWindow());
			role.setBegintime(new Timestamp(System.currentTimeMillis()));
			update(role);
		}
		
	}

}
