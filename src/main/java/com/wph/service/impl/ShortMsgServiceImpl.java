package com.wph.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.entities.Msg;
import com.wph.entities.Msgtype;
import com.wph.model.ShortMsg;
import com.wph.service.ShortMsgService;

@Service("shortMsgService")
public class ShortMsgServiceImpl extends BaseServiceImpl<ShortMsg> implements ShortMsgService {

	@Override
	public Map<String, Object> msgtoMap(ShortMsg msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer customer_id = msg.getCustomer_id();
		Integer customerservice_id = msg.getCustomerservice_id();
		Integer msgtype_id = msg.getMsgtype_id();
		String content = msg.getContent();
		Timestamp time = msg.getTime();
		if (customer_id != null) {
			map.put("customer_id", customer_id);
		}
		if (customerservice_id != null) {
			map.put("customerservice_id", customerservice_id);
		}
		if (msgtype_id != null) {
			map.put("msgtype_id", msgtype_id);
		}
		if (content != null) {
			map.put("content", content);
		}
		if (time != null) {
			map.put("time", time.toString());
		}
		return map;
	}

	@Override
	public ShortMsg saveShortMsg(Integer customer_id, Integer customerservice_id, Integer msgtype_id,String content,Timestamp time) {
		ShortMsg msg = new ShortMsg();
		Msg message = new Msg();
		if (customer_id != null) {
			msg.setCustomer_id(customer_id);
			message.setCustomer((Customer) getSession().get(Customer.class, customer_id));
		}
		if (customerservice_id != null) {
			msg.setCustomerservice_id(customerservice_id);
			message.setCustomerservice((Customerservice) getSession().get(Customerservice.class, customerservice_id));
		}
		if (msgtype_id != null) {
			msg.setMsgtype_id(msgtype_id);
			message.setMsgtype((Msgtype) getSession().get(Msgtype.class, msgtype_id));
		}else{
			System.out.println("msgtype_id²»ÄÜÎª¿Õ");
			return null;
		}
		if (content != null) {
			msg.setContent(content);
			message.setContent(content);
		}
		if (time != null) {
			msg.setTime(time);
			message.setSendtime(time);
		}
		getSession().save(message);
		return msg;
	}


}
