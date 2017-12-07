package com.wph.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wph.entities.Customer;
import com.wph.entities.Waitonline;
import com.wph.entities.Waitserviceonline;
import com.wph.entities.json.CustomerrfmJSON;
import com.wph.markov.SmartList;
import com.wph.model.CustomerWaitModel;
import com.wph.service.WaitonlineService;

@Service("waitonlineService")
public class WaitonlineServiceImpl extends BaseServiceImpl<Waitonline> implements WaitonlineService {

	/**
	 * 根据实际等待时间弹出一个客户
	 */
	@Override
	public synchronized Customer popCustomer() {
		System.out.println("popCustomer");
		String sql = "select w.customer_id from waitonline w where w.realtime=(select min(o.realtime) from waitonline o)";
		List<Integer> ids = getSession().createSQLQuery(sql).addScalar("customer_id", StandardBasicTypes.INTEGER)
				.list();
		Integer id = ids.get(0);
		Waitonline waitonline = get(id);
		delete(waitonline);
		String hql = "from Customer c where c.ctId = :id";
		return (Customer) getSession().createQuery(hql).setInteger("id", id).uniqueResult();
	}

	public synchronized Integer popCustomer(Integer companyid) {
		System.out.println("popCustomer");
		String sql = "select w.customer_id from waitonline w where w.customer_id in(select c.ct_id from customer c where c.company_id = :companyid) and w.realtime=(select min(o.realtime) from waitonline o)";
		List<Integer> ids = getSession().createSQLQuery(sql).addScalar("customer_id", StandardBasicTypes.INTEGER)
				.setInteger("companyid", companyid).list();
		if (ids.size() == 0) {
			return null;
		}
		Integer id = ids.get(0);
		Waitonline waitonline = get(id);
		delete(waitonline);
		return id;
	}

	// 非智能排队算法
	@Override
	public void pushCustomer(Integer customer_id) {
		System.out.println("pushCustomer");
		// 1.根据客户id获得客服对象
		Customer customer = null;
		String hql = "from Customer c where c.ctId = :id";
		customer = (Customer) getSession().createQuery(hql).setInteger("id", customer_id).uniqueResult();
		// 2.根据进来的客户重新计算排队时间
		// 3.将客户添加至队列
		Waitonline waitonline = new Waitonline();
		waitonline.setCustomer(customer);
		waitonline.setCustomerId(customer.getCtId());
		Timestamp time = new Timestamp(System.currentTimeMillis());
		waitonline.setBegintime(time);
		waitonline.setRealtime(time);
		getSession().save(waitonline);
	}

	// 智能排队算法
	@Override
	public void pushCustomer(Integer customerid, Integer companyid) {
		System.out.println("pushCustomer");
		// 1.根据客户id获得客户对象
		Customer customer = null;
		String hql = "from Customer c where c.ctId = :id";
		customer = (Customer) getSession().createQuery(hql).setInteger("id", customerid).uniqueResult();
		Waitonline waitonline = null;
		Timestamp time = null;
		try {

			// 2.将客户添加至队列
			waitonline = new Waitonline();
			waitonline.setCustomer(customer);
			waitonline.setCustomerId(customer.getCtId());
			time = new Timestamp(System.currentTimeMillis());
			waitonline.setBegintime(time);
			getSession().save(waitonline);
			getSession().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 3.重新计算排队时间
		waitonline.setBegintime(time);
		List<CustomerWaitModel> cts = getCustomerWaitModel(companyid);
		List<CustomerWaitModel> after = levelTransformRealTime(cts);
		for (CustomerWaitModel model : after) {
			Waitonline wait = get(model.getCtId());
			wait.setRealtime(model.getRealtime());
			update(wait);
		}

	}

	/**
	 * 判断等待队列是否已满
	 */
	private Integer rankslength;

	@Value("#{prop.rankslength}")
	// @Value表示去beans.xml文件中找id="prop"的bean，它是通过注解的方式读取properties配置文件的，然后去相应的配置文件中读取key=filePath的值
	public void setFilePath(Integer rankslength) {
		this.rankslength = rankslength;
	}

	@Override
	public Boolean isFree() {
		return true;
	}

	@Override
	public Boolean isFree(Integer companyid) {
		String sql = "select count(*) from waitonline w where w.customer_id in"
				+ " (select c.ct_id from customer c where c.company_id =:companyid)";
		Object length = getSession().createSQLQuery(sql).setInteger("companyid", companyid).uniqueResult();
		if (length == null) {
			return true;
		} else {

			if (((BigInteger) length).intValue() >= rankslength) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public List<CustomerWaitModel> levelTransformRealTime(List<CustomerWaitModel> cts) {
		long start = System.currentTimeMillis();
		int length = 100;
		long averageServiceTime = 60;// 设定平均服务时间
		SmartList l = new SmartList();
		l.getOrder(cts.toArray(new CustomerWaitModel[cts.size()]), averageServiceTime);
		for (int i = 0; i < cts.size(); i++) {

			System.out.println("i = " + i + "|ct = " + cts.get(i));
		}
		long runTime = System.currentTimeMillis() - start;
		System.out.println("运行耗时= " + runTime + " 毫秒");
		return cts;
	}

	@Override
	public Boolean isEmpty() {
		String hql = "select count(*) from Waitonline";
		Long count = (Long) getSession().createQuery(hql).uniqueResult();
		return count == 0;
	}

	@Override
	public Boolean isEmpty(Integer companyid) {
		String sql = "select count(*) from waitonline w where w.customer_id in (select o.ct_id from customer o where o.company_id = :companyid)";
		Object count = getSession().createSQLQuery(sql).setInteger("companyid", companyid).uniqueResult();
		if (count == null) {
			return true;
		} else {
			return ((BigInteger) count).intValue() == 0;
		}
	}

	@Override
	public void customerlogout(Integer id) {
		Waitonline wait = get(id);
		if (wait != null) {
			delete(wait);
		}
	}

	public List<CustomerWaitModel> getCustomerWaitModel(Integer companyid) {
		List<CustomerWaitModel> modellist = new ArrayList<CustomerWaitModel>();
		String sql1 = "select * from waitonline w where w.customer_id in (select c.ct_id from customer c where c.company_id = :companyid)";
		List<Object[]> waitlist = getSession().createSQLQuery(sql1).setInteger("companyid", companyid).list();
		for (Object[] o : waitlist) {
			Integer customer_id = (Integer) o[0];
			Timestamp begintime = null;
			Timestamp realtime = null;
			if (o[1] != null) {
				begintime = (Timestamp) o[1];
			}
			if (o[2] != null) {
				realtime = (Timestamp) o[2];
			}
			Double level = null;
			if (customer_id != null) {
				String sql2 = "select c.ct_level from customer c where c.ct_id = :customerid";
				Object lv = getSession().createSQLQuery(sql2).setInteger("customerid", customer_id).uniqueResult();
				if (lv != null) {
					level = (Double) lv;
				} else {
					level = (Double) 0.00;
				}
			}

			CustomerWaitModel model = new CustomerWaitModel();
			model.setBeginWaited(begintime);
			model.setCtId(customer_id);
			model.setCtLevel(level);
			model.setRealtime(realtime);
			modellist.add(model);

		}
		return modellist;

	}

}
