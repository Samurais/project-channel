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
	// =================================΢��������񷽷�===================================
	public String requestServiceByWeChat(){
		
		requestService();
		return requestService();
	}

	// ********************************************************************************
	// =================================�ͻ�������񷽷�===================================
	/**
	 * ���ͻ����͡��˹���ʱ���������action ���Ҹ���ת����customer_id,content,msgtype_id �鿴�Ƿ���У��Ŷӹ���
	 * ���߳�����������
	 */
	public String requestService() {
		System.out.println("WaitonlineAction:requestService");
		// ��Ϣ���ض����ʼ��
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
		// Msg��������ֵ
		// ��������Ӧ��Ϣ
		// �鿴�ͷ��Ŷӱ��Ƿ��п��пͷ�
		if (waitserviceonlineService.isFree(companyid)) {

			System.out.println("3204,3104");
			// ����һ���ͷ���֪ͨ�ͷ����룬״̬��3104
			Integer serviceid = waitserviceonlineService.popCustomerService(companyid);
			customerservice = customerserviceService.get(serviceid);
			// ����һ����Ϣ,֪ͨ�ͷ�����
			servicemsg = msgService.saveMsg(customerservice, customer, msgtypeService.get(3104), null, null, null);

			infHandle.msgSubmit(servicemsg);
			// ƥ��ͷ�,��webSocket֪ͨ�ͷ�,����response��ʾ�ͻ�����websocket����
			ShortMsg shortMsg =shortMsgService.saveShortMsg(customer_id, customerservice.getCsId(), 3204, null, new Timestamp(System.currentTimeMillis()));
			jsonMap = shortMsgService.msgtoMap(shortMsg);
			// ���Ự״̬����Ự״̬��
			chatonlineService.pushChatonline(servicemsg);
			// ���Ự��ʼ��Ϣд��Ự��¼��
			conversationService.beginConversation(servicemsg);
			return "jsonMap";
		} else {
			// **����ͷ��Ŷӱ�û�����ô���
			// ����response��ʾ�ͻ�û�����ô��ڲ�����websocket���ӵȴ�������֪ͨ
			// �鿴�ŶӶ����Ƿ�����
			System.out.println("WaitonlineAction:");
			if (waitonlineService.isFree(companyid)) {
				// **����ŶӶ���δ��
				// �����Ŷ�,���ͻ�д���Ŷӱ�
				//waitonlineService.pushCustomer(customer_id);
				waitonlineService.pushCustomer(customer_id,companyid);
				// ֪ͨ�ͻ��Ŷӣ�����Ҫ��ͻ�����WebSokcet����
				// ״̬�� 3205
				System.out.println("3205");
				msgtype = msgtypeService.get(3205);
				msgService.saveMsg(null, customer, msgtype, time, null, null);
				ShortMsg shortMsg = shortMsgService.saveShortMsg(customer_id, null, 3205, null, null);
				//֪ͨ�ͻ���Ҫ�Ŷ�,�����ÿͻ�����websokcet�ȴ��Ŷ�
				jsonMap = shortMsgService.msgtoMap(shortMsg);
				return "jsonMap";
			} else {
				// **����ŶӶ�������
				// ���ؿͻ��ȴ���������
				// ״̬��3206
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
