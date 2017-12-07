package com.wph.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.entities.Msg;
import com.wph.entities.Msgtype;
import com.wph.entities.json.MsgJSON;
import com.wph.entities.json.service.MsgJSONService;
import com.wph.model.ShortMsg;
import com.wph.service.MsgService;

import net.sf.json.JSONObject;

@Service("msgService")
public class MsgServiceImpl extends BaseServiceImpl<Msg> implements MsgService {

	@Override
	public Msg saveMsg(Customerservice customerservice, Customer customer, Msgtype msgtype, Timestamp sendtime,
			String content, String sensitiveword) {
		Msg msg = new Msg();
		if (customerservice != null) {
			msg.setCustomerservice(customerservice);
		}
		if (customer != null) {
			msg.setCustomer(customer);
		}
		if (msgtype != null) {
			msg.setMsgtype(msgtype);
		} else {
			System.out.println("MsgService:msgtype不能为空");
			return null;
		}
		if (sendtime != null) {
			msg.setSendtime(sendtime);
		} else {
			msg.setSendtime(new Timestamp(System.currentTimeMillis()));
		}
		if (content != null) {
			msg.setContent(content);
		}
		if (sensitiveword != null) {
			msg.setSensitiveword(sensitiveword);
		}
		getSession().save(msg);
		return msg;
	}

	@Override
	public Map<String, Object> msgtoMap(Msg msg) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ShortMsg msgtoShortMsg(Msg msg) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public JSONObject msgtoJson(Msg msg) {
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		// 消息的四个字段:1.客户id 2.客服id 3.消息类型 4.发送时间5.内容
		Customerservice customerservice = msg.getCustomerservice();
		Customer customer = msg.getCustomer();
		Msgtype msgtype = msg.getMsgtype();
		Timestamp sendtime = msg.getSendtime();
		String content = msg.getContent();
		if (customerservice != null) {
			jsonmap.put("customerservice_id", customerservice.getCsId());
		}
		if (customer != null) {
			jsonmap.put("customer_id", customer.getCtId());
		}
		if (msgtype != null) {
			jsonmap.put("msgtype_id", msgtype.getId());
		}
		if (sendtime != null) {
			jsonmap.put("sendtime", sendtime.toString());
		}
		if (content != null) {
			jsonmap.put("content", content);
		}
		JSONObject jsonObject = JSONObject.fromObject(jsonmap);
		return jsonObject;
	}

	// 根据公司ID查找所有聊天会话
	@Override
	public List<MsgJSON> pageQuery(Integer limit, Integer offset, String search, Integer cpId) {
		System.out.println("Msg:pageQuery");
		List<Object[]> msglist = null;
		if (search == null) {
			String sql = "select * from msg m where m.customerservice_id in(select c.cs_id from customerservice c where c.company_id=:cpId)";
			msglist = getSession().createSQLQuery(sql).setInteger("cpId", cpId).setFirstResult(offset)
					.setMaxResults(limit).list();
		}else{
			String sql = "select * from msg m where m.customerservice_id in(select c.cs_id from customerservice c where c.company_id=:cpId) and m.content like :search";
			msglist = getSession().createSQLQuery(sql).setInteger("cpId", cpId).setString("search", "%" + search + "%").setFirstResult(offset)
					.setMaxResults(limit).list();
		}
		
		return MsgJSONService.msgtoJSON(msglist);
	
	}

	@Override
	public BigInteger getCount(Integer companyid) {
		System.out.println("Msg:getCount");
		String sql = "select count(*) from msg m where m.customerservice_id in(select c.cs_id from customerservice c where c.company_id=:cpId)";
		return (BigInteger) getSession().createSQLQuery(sql).setInteger("cpId", companyid).uniqueResult();
	}

}
