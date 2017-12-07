package com.wph.action;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Msg;
import com.wph.entities.Order;
import com.wph.entities.json.OrderJSON;
import com.wph.model.ShortMsg;

@Controller("orderAction")
@Scope("prototype")
public class OrderAction extends BaseAction<OrderJSON> {
	// ********************************************************************************
	// ==================================订单保存部分=====================================
	public String save() {
		orderService.modelToOrder(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ============================客服请求订单确认部分=====================================
	public String confirm() {
		Order order = orderService.modelToOrder(model);
		Msg confirmmsg = new Msg();
		confirmmsg.setCustomer(customerService.get(model.getCustomerId()));
		confirmmsg.setCustomerservice(customerserviceService.get(model.getCustomerserviceId()));
		confirmmsg.setMsgtype(msgtypeService.get(1208));// 客服发客户订单确认请求
		confirmmsg.setContent(order.getId().toString());
		confirmmsg.setSendtime(new Timestamp(System.currentTimeMillis()));
		msgService.save(confirmmsg);

		// webConnect.send(confirmmsg, model.getCustomerId());
		infHandle.msgSubmit(confirmmsg);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ============================客户响应订单确认部分=====================================
	private ShortMsg shortMsg;

	// 当需要对接收对象的属性赋值时,需要有get方法
	public ShortMsg getShortMsg() {
		return shortMsg;
	}

	public void setShortMsg(ShortMsg shortMsg) {
		this.shortMsg = shortMsg;
	}

	private Integer orderid;

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public void responseconfirm() {
		Msg confirmresponsemsg = new Msg();

		confirmresponsemsg.setCustomer(customerService.get(shortMsg.getCustomer_id()));
		confirmresponsemsg.setCustomerservice(customerserviceService.get(shortMsg.getCustomerservice_id()));
		confirmresponsemsg.setMsgtype(msgtypeService.get(shortMsg.getMsgtype_id()));
		confirmresponsemsg.setContent(shortMsg.getContent());
		confirmresponsemsg.setSendtime(new Timestamp(System.currentTimeMillis()));
		msgService.save(confirmresponsemsg);
		if (shortMsg.getContent().equals("confirm")) {
			Order order = orderService.updateOrder(orderid, "notfinish");
			// 更新客户rfm值
			customerrfmService.updateCustomerrfm(shortMsg.getCustomer_id(), order);
			// 更新客户优先级
			customerrfmService.updateCustomerlevel(shortMsg.getCustomer_id());

		}
		if (shortMsg.getContent().equals("cancel")) {
			Order order = orderService.updateOrder(orderid, "cancel");
		}
		System.out.println("---------------------方法执行-----------------------");

		// webConnect.send(confirmresponsemsg,
		// shortMsg.getCustomerservice_id());
		infHandle.msgSubmit(confirmresponsemsg);
		return;
	}

	// ********************************************************************************
	// ===============================获取订单信息部分=====================================
	public String getOrder() {
		jsonMap = new HashMap<String, Object>();
		OrderJSON orderJSON = orderService.getOrder(model.getId());
		String picpath = productService.getpic(orderJSON.getProduct_id());
		jsonMap.put("orderJSON", orderJSON);
		jsonMap.put("picpath", picpath);
		return "jsonMap";
	}

	// ********************************************************************************
	// ================================获取客服订单数量====================================
	public String getTotalCount() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", orderService.getTotalCount(model.getCustomerserviceId()));
		return "jsonMap";
	}
	// ================================获取客服所有订单====================================
	// ********************************************************************************

	public String getTotalOrder() {
		List<OrderJSON> orderJSONList = new ArrayList<OrderJSON>();
		orderJSONList = orderService.getTotalOrder(model.getCustomerserviceId(), limit, offset, search);
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", orderService.getTotalCount(model.getCustomerserviceId()));
		jsonMap.put("rows", orderJSONList);
		return "jsonMap";
	}

	// ===========================获取客服以完成订单数量====================================
	// ********************************************************************************
	public String getFinishCount() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", orderService.getCountFinish(model.getCustomerserviceId()));
		return "jsonMap";
	}

	// ===========================获取客服未完成订单数量====================================
	// ********************************************************************************
	public String getNotFinishCount() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", orderService.getCountNotFinish(model.getCustomerserviceId()));
		return "jsonMap";
	}

	// ================================获取完成订单=======================================
	// ********************************************************************************
	public String getOrderFinish() {
		List<OrderJSON> orderJSONList = new ArrayList<OrderJSON>();
		orderJSONList = orderService.getOrderJSONFinish(model.getCustomerserviceId(), limit, offset, search);
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", orderService.getCountFinish(model.getCustomerserviceId()));
		jsonMap.put("rows", orderJSONList);
		return "jsonMap";
	}

	// ********************************************************************************
	// ================================获取未完成订单=====================================
	public String getOrderNotFinish() {
		List<OrderJSON> orderJSONList = new ArrayList<OrderJSON>();
		orderJSONList = orderService.getOrderJSONNotFinish(model.getCustomerserviceId(), limit, offset, search);
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", orderService.getCountNotFinish(model.getCustomerserviceId()));
		jsonMap.put("rows", orderJSONList);
		return "jsonMap";
	}

	// ********************************************************************************
	// ================================获取公司订单=======================================
	public String getCompanyOrder() {
		List<OrderJSON> orderJSONList = new ArrayList<OrderJSON>();
		orderJSONList = orderService.getOrderJSONCompany(model.getCompany_id(), limit, offset, search);
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", orderService.getCountCompanyOrder(model.getCompany_id()));
		jsonMap.put("rows", orderJSONList);
		return "jsonMap";
	}

	// ********************************************************************************
	// =================================delete方法======================================
	public String finishByIds() {
		System.out.println(ids);
		orderService.finishByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}

}
