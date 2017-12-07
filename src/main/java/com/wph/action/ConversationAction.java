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
	// =================================������˾����=====================================
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	
	// ********************************************************************************
	// ==========================�Ự�����������=========================================


	public Integer getCompanyid() {
		return companyid;
	}

	public String conversationEnd() {
		// �����߻Ự���ȥ�Ự
		ChatonlineId chatonlineId = new ChatonlineId();
		Integer companyid = customerService.getCompanyid(model.getCustomer_id());
		chatonlineId.setCustomerserviceId(model.getCustomerservice_id());
		chatonlineId.setCustomerId(model.getCustomer_id());
		chatonlineService.popChatonline(chatonlineId);

		// ������Ϣ֪ͨ�ͻ���д�����
		Customer customer = customerService.get(model.getCustomer_id());
		Customerservice customerservice = customerserviceService.get(model.getCustomerservice_id());
		Msgtype msgtype = msgtypeService.get(3207);
		Msg msg = msgService.saveMsg(customerservice, customer, msgtype, null, null, null);

		// ͨ��websocket������Ϣ
		//webConnect.send(msg, model.getCustomer_id());
		System.out.println(model.getCustomer_id());
		infHandle.msgSubmit(msg);
		
		// �ͷ��Ⱥ�������Ͽͷ�
		waitserviceonlineService.pushCustomerService(customerserviceService.get(model.getCustomerservice_id()));
		// ��֤�Ƿ����Ŷ��û�
		// �����,��֪ͨ�ͻ�����
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
			// ��websocket֪ͨ�ͻ�����

			infHandle.msgSubmit(customermsg);
			
			Msg servicemsg = new Msg();
			servicemsg.setCustomer(newcustomer);
			servicemsg.setCustomerservice(newcustomerservice);
			servicemsg.setMsgtype(msgtypeService.get(3104));
			//֪ͨ�ͷ�����
			
			infHandle.msgSubmit(servicemsg);
			
			chatonlineService.pushChatonline(servicemsg);
			conversationService.beginConversation(servicemsg);
		}
		// �жϿͻ��Ƿ�����
		
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ============================��д���������=========================================
	public String setDegree() {
		Conversation conversation = conversationService.getLatelyConversation(model.getCustomer_id(),
				model.getCustomerservice_id());
		// ��д�û������
		conversationService.setDegree(model, conversation);
		// ��д�Ự�ؼ���
		conversationService.setKeyWord(model, conversation);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================��Ϣ��¼��ҳ��ѯ===================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", conversationService.getCount(companyid));
		jsonMap.put("rows", conversationService.pageQuery(limit, offset, search, companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// =================================��Ϣ��¼��ҳ��ѯ===================================
	public String pageQueryByService() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", conversationService.getCountByService(model.getCustomerservice_id()));
		jsonMap.put("rows",
				conversationService.pageQueryByService(limit, offset, search, model.getCustomerservice_id()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ====================================����û��ȴ�===================================
	public String getHotWord() {
		jsonList = new ArrayList<HotWordJSON>();
		jsonList = conversationService.getHotWordLastMonth(companyid);

		return "jsonList";
	}
}
