package com.wph.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.entities.Company;
import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.entities.Order;
import com.wph.entities.Product;
import com.wph.entities.json.OrderJSON;
import com.wph.service.OrderService;

@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
	/**
	 * OrderJSON��תOrder��
	 */
	@Override
	public Order modelToOrder(OrderJSON model) {
		Order order = new Order();
		// ���ÿͻ�ID
		if (model.getCustomerId() != null) {
			Object customer = getSession().get(Customer.class, model.getCustomerId());
			if (customer != null) {
				order.setCustomerId(model.getCustomerId());
			} else {
				order.setCustomerId(null);
			}
		}
		// ���ö���ʱ��
		order.setTime(new Timestamp(System.currentTimeMillis()));
		// ���ÿͷ�ID
		if (model.getCustomerserviceId() != null) {
			Object customerservice = getSession().get(Customerservice.class, model.getCustomerserviceId());
			if (customerservice != null) {
				order.setCustomerserviceId(model.getCustomerserviceId());
			} else {
				order.setCustomerserviceId(null);
			}
		}
		// ���ò�Ʒ
		if (model.getProduct_id() != null) {
			Object product = getSession().get(Product.class, model.getProduct_id());
			order.setProduct((Product) product);
		}
		// ���ù�˾
		if (model.getCompany_id() != null) {
			Object company = getSession().get(Company.class, model.getCompany_id());
			order.setCompany((Company) company);
		}
		// ���ý���ʱ��
		Timestamp time = new Timestamp(System.currentTimeMillis());
		// ��������ת�����ܻ����
		if (model.getDemandtime() != null) {
			System.out.println(model.getDemandtime());
			SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
			// sdf.setLenient(false);
			Date date = null;
			try {
				date = sdf.parse(model.getDemandtime());
			} catch (ParseException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			System.out.println(date.toString());
			Timestamp demandtime = new Timestamp((new Date()).getTime());
			order.setDemandtime(demandtime);
		}

		// ���ý��յص�
		order.setDemandaddr(model.getDemandaddr());
		// ���ý��յ绰
		order.setDemandphone(model.getDemandphone());
		// ������������
		order.setDemandotherrequest(model.getDemandotherrequest());
		order.setStatus("waitconfirm");
		save(order);
		return order;
	}

	/**
	 * ��õ���������Ϣ
	 */
	@Override
	public OrderJSON getOrder(Integer orderid) {
		OrderJSON orderJSON = new OrderJSON();
		Order order = get(orderid);
		Integer id = order.getId();
		Product product = order.getProduct();
		Company company = order.getCompany();
		Integer customerid = order.getCustomerId();
		Integer customerserviceid = order.getCustomerserviceId();
		String demandaddr = order.getDemandaddr();
		String demandotherrequest = order.getDemandotherrequest();
		Integer demandphone = order.getDemandphone();
		Timestamp demandtime = order.getDemandtime();

		orderJSON.setId(id);
		if (product != null) {
			orderJSON.setProduct_id(product.getId());
		}
		if (company != null) {
			orderJSON.setCompany_id(company.getCpId());
		}
		orderJSON.setCustomerId(customerid);
		orderJSON.setCustomerserviceId(customerserviceid);
		orderJSON.setDemandaddr(demandaddr);
		orderJSON.setDemandotherrequest(demandotherrequest);
		orderJSON.setDemandphone(demandphone);
		if (demandtime != null) {
			orderJSON.setDemandtime(demandtime.toString());
		}
		return orderJSON;
	}

	@Override
	public Order updateOrder(Integer orderid, String status) {
		Order order = get(orderid);
		order.setStatus(status);
		update(order);
		return order;
	}

	/**
	 * ����OrderJSON��
	 */
	@Override
	public List<OrderJSON> getOrderJSONFinish(Integer customerserviceId) {
		if (customerserviceId == null)
			return null;
		// String sql = "select o.id from order o where o.customerservice_id =
		// :customerserviceId and o.status='finish'";
		// List<Object> serviceids =
		// getSession().createSQLQuery(sql).setInteger("customerserviceId",
		// customerserviceId).list();
		String hql = "select o.id from Order o where o.customerserviceId = :customerserviceId and o.status='notfinish'";
		List<Object> serviceids = getSession().createQuery(hql).setInteger("customerserviceId", customerserviceId)
				.list();
		List<OrderJSON> orderJSON = new ArrayList<OrderJSON>();
		for (Object id : serviceids) {
			Order order = get((Integer) id);
			orderJSON.add(orderToOrderJSON(order));
		}
		return orderJSON;
	}

	@Override
	public Long getCountFinish(Integer customerserviceId) {
		String hql = "select count(*) from Order o where o.customerserviceId = :customerserviceId and o.status='finish'";
		return (Long) getSession().createQuery(hql).setInteger("customerserviceId", customerserviceId).uniqueResult();
	}

	@Override
	public List<OrderJSON> getOrderJSONFinish(Integer customerserviceId, Integer limit, Integer offset, String search) {
		if (customerserviceId == null)
			return null;
		String hql = null;
		List<Order> orderlist = null;
		if (search != null) {
			hql = "from Order o where o.customerserviceId = :customerserviceId and o.status='finish' and o.demandotherrequest like:search";
			orderlist = getSession().createQuery(hql).setString("search", "%" + search + "%").setFirstResult(offset)
					.setMaxResults(limit).setInteger("customerserviceId", customerserviceId).list();
		} else {
			hql = "from Order o where o.customerserviceId = :customerserviceId and o.status='finish'";
			orderlist = getSession().createQuery(hql).setFirstResult(offset).setMaxResults(limit)
					.setInteger("customerserviceId", customerserviceId).list();
		}
		List<OrderJSON> orderJSON = new ArrayList<OrderJSON>();
		for (Order order : orderlist) {
			orderJSON.add(orderToOrderJSON(order));
		}
		return orderJSON;
	}

	@Override
	public Long getCountNotFinish(Integer customerserviceId) {
		String hql = "select count(*) from Order o where o.customerserviceId = :customerserviceId and o.status='notfinish'";
		return (Long) getSession().createQuery(hql).setInteger("customerserviceId", customerserviceId).uniqueResult();
	}

	@Override
	public List<OrderJSON> getOrderJSONNotFinish(Integer customerserviceId, Integer limit, Integer offset,
			String search) {
		if (customerserviceId == null)
			return null;
		String hql = null;
		List<Order> orderlist = null;
		if (search != null) {
			hql = "from Order o where o.customerserviceId = :customerserviceId and o.status='notfinish' and o.demandotherrequest like:search";
			orderlist = getSession().createQuery(hql).setString("search", "%" + search + "%").setFirstResult(offset)
					.setMaxResults(limit).setInteger("customerserviceId", customerserviceId).list();
		} else {
			hql = "from Order o where o.customerserviceId = :customerserviceId and o.status='notfinish'";
			orderlist = getSession().createQuery(hql).setFirstResult(offset).setMaxResults(limit)
					.setInteger("customerserviceId", customerserviceId).list();
		}
		List<OrderJSON> orderJSON = new ArrayList<OrderJSON>();
		for (Order order : orderlist) {
			orderJSON.add(orderToOrderJSON(order));
		}
		return orderJSON;
	}

	/**
	 * OrderתOrderJSON����
	 */
	private OrderJSON orderToOrderJSON(Order order) {
		OrderJSON orderJSON = new OrderJSON();
		// ����ID
		orderJSON.setId(order.getId());
		// ���ù�˾
		Company company = order.getCompany();
		if (company != null) {
			orderJSON.setCompany_id(company.getCpId());
		}
		// ���ò�Ʒ
		Product product = order.getProduct();
		if (product != null) {
			orderJSON.setProduct_id(product.getId());
		}
		// �ͻ�Ҫ���ʹ�ʱ��
		Timestamp demandtime = order.getDemandtime();
		if (demandtime != null) {
			orderJSON.setDemandtime(demandtime.toString());
		}
		// �������ʱ��
		Timestamp time = order.getTime();
		if (time != null) {
			orderJSON.setTime(time.toString());
		}
		orderJSON.setCustomerId(order.getCustomerId());
		orderJSON.setCustomerserviceId(order.getCustomerserviceId());
		orderJSON.setDemandaddr(order.getDemandaddr());
		orderJSON.setDemandphone(order.getDemandphone());
		orderJSON.setDemandotherrequest(order.getDemandotherrequest());
		orderJSON.setStatus(order.getStatus());
		return orderJSON;
	}

	@Override
	public Long getCountCompanyOrder(Integer companyid) {
		String hql = "select count(*) from Order o where o.company.cpId = :companyid";
		return (Long) getSession().createQuery(hql).setInteger("companyid", companyid).uniqueResult();
	}

	@Override
	public List<OrderJSON> getOrderJSONCompany(Integer companyid, Integer limit, Integer offset, String search) {
		if (companyid == null)
			return null;
		String hql = null;
		List<Order> orderlist = null;
		if (search != null) {
			hql = "from Order o where o.company.cpId = :companyid and o.demandotherrequest like:search";
			orderlist = getSession().createQuery(hql).setString("search", "%" + search + "%").setFirstResult(offset)
					.setMaxResults(limit).setInteger("companyid", companyid).list();
		} else {
			hql = "from Order o where o.company.cpId = :companyid";
			orderlist = getSession().createQuery(hql).setFirstResult(offset).setMaxResults(limit)
					.setInteger("companyid", companyid).list();
		}
		List<OrderJSON> orderJSON = new ArrayList<OrderJSON>();
		for (Order order : orderlist) {
			orderJSON.add(orderToOrderJSON(order));
		}
		return orderJSON;
	}

	@Override
	public Long getTotalCount(Integer customerserviceId) {
		String hql = "select count(*) from Order o where o.customerserviceId = :customerserviceId";
		return (Long) getSession().createQuery(hql).setInteger("customerserviceId", customerserviceId).uniqueResult();
	}

	@Override
	public List<OrderJSON> getTotalOrder(Integer customerserviceId, Integer limit, Integer offset, String search) {
		if (customerserviceId == null)
			return null;
		String hql = null;
		List<Order> orderlist = null;
		if (search != null) {
			hql = "from Order o where o.customerserviceId = :customerserviceId and o.demandotherrequest like:search";
			orderlist = getSession().createQuery(hql).setString("search", "%" + search + "%").setFirstResult(offset)
					.setMaxResults(limit).setInteger("customerserviceId", customerserviceId).list();
		} else {
			hql = "from Order o where o.customerserviceId = :customerserviceId ";
			orderlist = getSession().createQuery(hql).setFirstResult(offset).setMaxResults(limit)
					.setInteger("customerserviceId", customerserviceId).list();
		}
		List<OrderJSON> orderJSON = new ArrayList<OrderJSON>();
		for (Order order : orderlist) {
			orderJSON.add(orderToOrderJSON(order));
		}
		return orderJSON;
	}

	@Override
	public void finishByIds(String ids) {
		String hql = "update Order o set o.status='finish' where o.id in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();
	}

}
