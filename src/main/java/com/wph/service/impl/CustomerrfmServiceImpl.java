package com.wph.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wph.entities.Customer;
import com.wph.entities.Customerrfm;
import com.wph.entities.Order;
import com.wph.entities.json.CustomerrfmJSON;
import com.wph.entities.json.RfmScatterJSON;
import com.wph.entities.json.service.ConversationJSONService;
import com.wph.entities.json.service.CustomerrfmJSONService;
import com.wph.entities.json.service.RfmScatterJSONService;
import com.wph.kmeans.kmeans;
import com.wph.model.CustomerrfmModel;
import com.wph.service.CustomerrfmService;

@Service("customerrfmService")
public class CustomerrfmServiceImpl extends BaseServiceImpl<Customerrfm> implements CustomerrfmService {

	@Override
	public void updateCustomerrfm(Integer customerid, Order order) {
		String hql = "from Customerrfm c where c.customer.ctId = :customerid";
		String sql = "select p.price from product p where p.id=(select o.product_id from access.order o where o.id = :orderid)";
		Object pric = getSession().createSQLQuery(sql).setInteger("orderid", order.getId()).uniqueResult();
		if (pric == null) {
			return;
		}
		Integer price = (Integer) pric;
		List<Customerrfm> customerrfmlist = getSession().createQuery(hql).setInteger("customerid", customerid).list();
		if (customerrfmlist.size() == 0) {
			Customerrfm newcustomerrfm = new Customerrfm();
			newcustomerrfm.setCustomer((Customer) getSession().get(Customer.class, customerid));
			newcustomerrfm.setLastbuytime(order.getTime());
			newcustomerrfm.setMonthbuytimes(1);
			newcustomerrfm.setMonthbuysum(price);
			save(newcustomerrfm);
		} else {
			Customerrfm customerrfm = customerrfmlist.get(0);
			// 获取当前月份
			Calendar now = Calendar.getInstance();
			Integer currentmonth = (now.get(Calendar.MONTH) + 1);
			// 获取客户rfm记录月份
			Timestamp time = customerrfm.getLastbuytime();
			Integer lastbuytime = (time.getMonth() + 1);

			// 如果上次购买在本月
			if (currentmonth == lastbuytime) {
				// 更新上次购买时间
				customerrfm.setLastbuytime(order.getTime());
				customerrfm.setMonthbuytimes(customerrfm.getMonthbuytimes() + 1);
				customerrfm.setMonthbuysum(customerrfm.getMonthbuysum() + price);
				update(customerrfm);
			}
			if (currentmonth > lastbuytime) {
				customerrfm.setLastbuytime(order.getTime());
				customerrfm.setLastmonthbuytimes(customerrfm.getMonthbuytimes());
				customerrfm.setLastmonthbuysum(customerrfm.getMonthbuysum());
				customerrfm.setMonthbuysum(price);
				customerrfm.setMonthbuytimes(1);
				update(customerrfm);
			}
		}

	}

	// 簇的数量
	private Integer k;

	@Value("#{prop.k}")
	public void setK(Integer k) {
		this.k = k + 1;
	}

	@Override
	public void updateCustomerlevel(Integer customerid) {
		// 获取顾客所在公司
		Integer companyid = null;
		String sql1 = "select c.company_id from customer c where c.ct_id = :customerid";
		Object cpid = getSession().createSQLQuery(sql1).setInteger("customerid", customerid).uniqueResult();
		if (cpid == null) {
			return;
		}
		companyid = (Integer) cpid;

		// 获取顾客的数量
		String sql2 = "select count(*) from customerrfm c where c.customer_id in(select o.ct_id from customer o where o.company_id =:companyid)";
		Object count = getSession().createSQLQuery(sql2).setInteger("companyid", companyid).uniqueResult();
		if (count == null) {
			return;
		}

		if (((BigInteger) count).intValue() == 0) {
			return;
		} else {
			if (((BigInteger) count).intValue() < k) {
				k = ((BigInteger) count).intValue();
			}
		}
		String sql3 = "select * from customerrfm c where c.customer_id in(select o.ct_id from customer o where o.company_id =:companyid)";
		List<Object[]> oblist = getSession().createSQLQuery(sql3).setInteger("companyid", companyid).list();
		if (oblist.size() == 0) {
			return;
		}
		List<CustomerrfmModel> customerrfmModelList = new ArrayList<CustomerrfmModel>();
		Timestamp mintime = null;
		for (Object[] ob : oblist) {
			CustomerrfmModel model = new CustomerrfmModel();

			if (ob[1] != null) {
				model.setId((Integer) ob[1]);
			}
			if (ob[2] != null) {
				model.setLastbuytime((Timestamp) ob[2]);
			} else {
				model.setLastbuytime(null);
			}
			if (ob[3] != null) {
				model.setMonthbuytimes((Integer) ob[3]);
			} else {
				model.setMonthbuysum(0);
			}
			if (ob[4] != null) {
				model.setLastmonthbuytimes((Integer) ob[4]);
			} else {
				model.setLastmonthbuytimes(0);
			}
			if (ob[5] != null) {
				model.setMonthbuysum((Integer) ob[5]);
			} else {
				model.setMonthbuysum(0);
			}
			if (ob[6] != null) {
				model.setLastmonthbuysum((Integer) ob[6]);
			} else {
				model.setLastmonthbuysum(0);
			}
			customerrfmModelList.add(model);
		}

		kmeans ks = new kmeans();
		Map<Integer, Double> map = ks.kmeans(customerrfmModelList, k);
		for (Map.Entry<Integer, Double> entry : map.entrySet()) {
			Customer customer = (Customer) getSession().get(Customer.class, entry.getKey());
			customer.setCtLevel(entry.getValue() + 1);
		}
		System.out.println(map.toString());
	}

	@Override
	public BigInteger getCount(Integer companyid) {
		System.out.println("Customerrfm:getCount");
		String sql = "select count(*) from customerrfm c where c.customer_id in(select t.ct_id from customer t where t.company_id=:cpId)";
		return (BigInteger) getSession().createSQLQuery(sql).setInteger("cpId", companyid).uniqueResult();
	}

	@Override
	public List<CustomerrfmJSON> pageQuery(Integer limit, Integer offset, String search, Integer cpId) {
		System.out.println("Customerrfm:pageQuery");
		List<Object[]> rfmlist = null;
		if (search == null) {
			String sql = "select * from customerrfm c  where c.customer_id in(select t.ct_id from customer t where t.company_id=:cpId)";
			rfmlist = getSession().createSQLQuery(sql).setInteger("cpId", cpId).setFirstResult(offset)
					.setMaxResults(limit).list();
		} else {
			String sql = "select * from customerrfm c  where c.customer_id in(select t.ct_id from customer t where t.company_id=:cpId) and c.customer_id like:search";
			rfmlist = getSession().createSQLQuery(sql).setInteger("cpId", cpId).setString("search", "%" + search + "%")
					.setFirstResult(offset).setMaxResults(limit).list();
		}

		return CustomerrfmJSONService.customerrfmtoJSON(rfmlist);
	}

	@Override
	public List<RfmScatterJSON> getRfmScatter(Integer companyid) {
		System.out.println("Customerrfm:getRfmScatter");
		List<Object[]> rfmlist = null;
		String sql = "select * from customerrfm c  where c.customer_id in(select t.ct_id from customer t where t.company_id=:cpId)";
		rfmlist = getSession().createSQLQuery(sql).setInteger("cpId", companyid).list();
		
		return RfmScatterJSONService.customerrfmtoJSON(rfmlist);
	}

}
