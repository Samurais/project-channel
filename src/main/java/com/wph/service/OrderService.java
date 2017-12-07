package com.wph.service;

import java.util.List;

import com.wph.entities.Order;
import com.wph.entities.json.OrderJSON;

public interface OrderService extends BaseService<Order>{

	public Order modelToOrder(OrderJSON model);
	public OrderJSON getOrder(Integer orderid);
	public Order updateOrder(Integer orderid,String status);
	
	public List<OrderJSON> getOrderJSONFinish(Integer customerserviceId);
	public Long getCountFinish(Integer customerserviceId);
	public List<OrderJSON> getOrderJSONFinish(Integer customerserviceId, Integer limit, Integer offset, String search);
	
	public Long getCountNotFinish(Integer customerserviceId);
	public List<OrderJSON> getOrderJSONNotFinish(Integer customerserviceId, Integer limit, Integer offset, String search);

	public Long getCountCompanyOrder(Integer companyid);
	public List<OrderJSON> getOrderJSONCompany(Integer companyid, Integer limit, Integer offset, String search);

	public Long getTotalCount(Integer customerserviceId);
	public List<OrderJSON> getTotalOrder(Integer customerserviceId, Integer limit, Integer offset, String search);
	
	public void finishByIds(String ids);
}
