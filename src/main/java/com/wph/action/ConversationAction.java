package com.wph.action;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.connect.service.solve.WebConnectServiceSolve;
import com.wph.entities.ChatonlineId;
import com.wph.entities.Conversation;
import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.entities.Msg;
import com.wph.entities.Msgtype;
import com.wph.entities.json.HotWordJSON;
import com.wph.model.ShortMsg;

@Controller("conversationAction")
@Scope("prototype")
public class ConversationAction extends BaseAction<ShortMsg> {
	// ********************************************************************************
	// =================================辨明公司号码=====================================
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	
	// ********************************************************************************
	// ==========================会话结束请求对象=========================================


	public Integer getCompanyid() {
		return companyid;
	}

	public String conversationEnd() {
		// 把在线会话表除去会话
		ChatonlineId chatonlineId = new ChatonlineId();
		Integer companyid = customerService.getCompanyid(model.getCustomer_id());
		chatonlineId.setCustomerserviceId(model.getCustomerservice_id());
		chatonlineId.setCustomerId(model.getCustomer_id());
		chatonlineService.popChatonline(chatonlineId);

		// 发送消息通知客户填写满意表
		Customer customer = customerService.get(model.getCustomer_id());
		Customerservice customerservice = customerserviceService.get(model.getCustomerservice_id());
		Msgtype msgtype = msgtypeService.get(3207);
		Msg msg = msgService.saveMsg(customerservice, customer, msgtype, null, null, null);

		// 通过websocket发送消息
		//webConnect.send(msg, model.getCustomer_id());
		System.out.println(model.getCustomer_id());
		infHandle.msgSubmit(msg);
		
		// 客服等候服务表加上客服
		waitserviceonlineService.pushCustomerService(customerserviceService.get(model.getCustomerservice_id()));
		// 验证是否还有排队用户
		// 如果有,则通知客户进入
		if (!waitonlineService.isEmpty(companyid)) {
			System.out.println("waitonlineService.isEmpty=false");
			Integer serviceid =  waitserviceonlineService.popCustomerService(companyid);
			Customerservice newcustomerservice = customerserviceService.get(serviceid);
			
			Integer customerid = waitonlineService.popCustomer(companyid);
			Customer newcustomer = customerService.get(customerid);
			Msg customermsg = new Msg();
			customermsg.setCustomer(newcustomer);
			customermsg.setCustomerservice(newcustomerservice);
			customermsg.setMsgtype(msgtypeService.get(3204));
			// 用websocket通知客户进入

			infHandle.msgSubmit(customermsg);
			
			Msg servicemsg = new Msg();
			servicemsg.setCustomer(newcustomer);
			servicemsg.setCustomerservice(newcustomerservice);
			servicemsg.setMsgtype(msgtypeService.get(3104));
			//通知客服进入
			
			infHandle.msgSubmit(servicemsg);
			
			chatonlineService.pushChatonline(servicemsg);
			conversationService.beginConversation(servicemsg);
		}
		// 判断客户是否在线
		
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ============================填写满意度请求=========================================
	public String setDegree() {
		Conversation conversation = conversationService.getLatelyConversation(model.getCustomer_id(),
				model.getCustomerservice_id());
		// 填写用户满意度
		conversationService.setDegree(model, conversation);
		// 填写会话关键字
		conversationService.setKeyWord(model, conversation);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================消息记录分页查询===================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", conversationService.getCount(companyid));
		jsonMap.put("rows", conversationService.pageQuery(limit, offset, search, companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// =================================消息记录分页查询===================================
	public String pageQueryByService() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", conversationService.getCountByService(model.getCustomerservice_id()));
		jsonMap.put("rows",
				conversationService.pageQueryByService(limit, offset, search, model.getCustomerservice_id()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ====================================获得用户热词===================================
	public String getHotWord() {
		jsonList = new ArrayList<HotWordJSON>();
		jsonList = conversationService.getHotWordLastMonth(companyid);

		return "jsonList";
	}
}
