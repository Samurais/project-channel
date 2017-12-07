package com.wph.entities.json.service;

import java.util.ArrayList;
import java.util.List;

import com.wph.entities.json.MsgJSON;

public class MsgJSONService {
	public static List<MsgJSON> msgtoJSON(List<Object[]> msglist) {
		List<MsgJSON> msgJSONlist = new ArrayList<MsgJSON>();
		for (Object[] o : msglist) {
			Integer id = (Integer) o[0];
			String sendtime = null;
			if (o[1] != null) {
				sendtime = (String) o[1].toString();
			}
			String content = (String) o[2];
			String sensitiveword = (String) o[3];
			Integer msgtype_id = (Integer) o[4];
			Integer customer_id = (Integer) o[5];
			Integer customerservice_id = (Integer) o[6];
			MsgJSON msg = new MsgJSON();
			msg.setId(id);
			msg.setSendtime(sendtime);
			msg.setContent(content);
			msg.setSensitiveword(sensitiveword);
			msg.setMsgtype_id(msgtype_id);
			msg.setCustomer_id(customer_id);
			msg.setCustomerservice_id(customerservice_id);
			msgJSONlist.add(msg);
		}
		return msgJSONlist;

	}
}
