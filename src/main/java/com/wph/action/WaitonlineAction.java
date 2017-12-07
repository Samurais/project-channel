package com.wph.action;

import java.sql.Timestamp;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.entities.Msg;
import com.wph.entities.Msgtype;
import com.wph.model.ShortMsg;

@Controller("waitonlineAction")
@Scope("prototype")
public class WaitonlineAction extends BaseAction<ShortMsg> {
	// ********************************************************************************
	// =================================微信请求服务方法===================================
	public String requestServiceByWeChat(){
		
		requestService();
		return requestService();
	}

	// ********************************************************************************
	// =================================客户请求服务方法===================================
	/**
	 * 当客户发送“人工”时，请求到这个action 并且附带转发的customer_id,content,msgtype_id 查看是否空闲，排队过滤
	 * 用线程锁控制请求
	 */
	public String requestService() {
		System.out.println("WaitonlineAction:requestService");
		// 消息返回对象初始化
		Integer customer_id = model.getCustomer_id();
		Msg customermsg = new Msg();
		Msg servicemsg = new Msg();
		jsonMap = new HashMap<String,Object>();
		Customer customer = null;
		Msgtype msgtype = null;
		Customerservice customerservice =null;
		Timestamp time = null;
		customer = customerService.get(customer_id);
		Integer companyid = customerService.getCompanyid(customer_id);
		time = new Timestamp(System.currentTimeMillis());
		// Msg参数对象赋值
		// 服务器相应消息
		// 查看客服排队表是否有空闲客服
		if (waitserviceonlineService.isFree(companyid)) {

			System.out.println("3204,3104");
			// 弹出一个客服，通知客服进入，状态码3104
			Integer serviceid = waitserviceonlineService.popCustomerService(companyid);
			customerservice = customerserviceService.get(serviceid);
			// 返回一个消息,通知客服进入
			servicemsg = msgService.saveMsg(customerservice, customer, msgtypeService.get(3104), null, null, null);

			infHandle.msgSubmit(servicemsg);
			// 匹配客服,用webSocket通知客服,返回response提示客户建立websocket连接
			ShortMsg shortMsg =shortMsgService.saveShortMsg(customer_id, customerservice.getCsId(), 3204, null, new Timestamp(System.currentTimeMillis()));
			jsonMap = shortMsgService.msgtoMap(shortMsg);
			// 将会话状态加入会话状态表
			chatonlineService.pushChatonline(servicemsg);
			// 将会话开始信息写入会话记录表
			conversationService.beginConversation(servicemsg);
			return "jsonMap";
		} else {
			// **如果客服排队表没有闲置窗口
			// 返回response提示客户没有闲置窗口并建立websocket连接等待服务器通知
			// 查看排队队列是否已满
			System.out.println("WaitonlineAction:");
			if (waitonlineService.isFree(companyid)) {
				// **如果排队队列未满
				// 进入排队,将客户写入排队表
				//waitonlineService.pushCustomer(customer_id);
				waitonlineService.pushCustomer(customer_id,companyid);
				// 通知客户排队，并且要求客户建立WebSokcet连接
				// 状态码 3205
				System.out.println("3205");
				msgtype = msgtypeService.get(3205);
				msgService.saveMsg(null, customer, msgtype, time, null, null);
				ShortMsg shortMsg = shortMsgService.saveShortMsg(customer_id, null, 3205, null, null);
				//通知客户需要排队,并且让客户发起websokcet等待排队
				jsonMap = shortMsgService.msgtoMap(shortMsg);
				return "jsonMap";
			} else {
				// **如果排队队列已满
				// 返回客户等待队列已满
				// 状态码3206
				System.out.println("3206");
				customer = customerService.get(customer_id);
				msgtype = msgtypeService.get(3206);
				time = new Timestamp(System.currentTimeMillis());
				customermsg.setCustomer(customer);
				customermsg.setMsgtype(msgtype);
				customermsg.setSendtime(time);
				jsonMap.put("customer_id", customer_id);
				jsonMap.put("msgtype_id", 3206);
				msgService.save(customermsg);
				return "jsonMap";
			}
		}
	}
	

}
