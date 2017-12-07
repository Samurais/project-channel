package com.wph.entities.json.service;

import java.util.ArrayList;
import java.util.List;

import com.wph.entities.json.ConversationJSON;

public class ConversationJSONService {
	public static List<ConversationJSON> msgtoJSON(List<Object[]> msglist) {
		List<ConversationJSON> conJSONlist = new ArrayList<ConversationJSON>();
		for (Object[] o : msglist) {
			Integer id = (Integer) o[0];
			String begintime = null;
			String endtime = null;
			if (o[1] != null) {
				begintime = (String) o[1].toString();
			}
			if (o[2] != null) {
				endtime = (String) o[2].toString();
			}
			String keyword = (String) o[3];
			String degree = (String) o[4];
			Integer customer_id = (Integer) o[5];
			Integer customerservice_id = (Integer) o[6];
			String firstkeyword = (String) o[7];
			ConversationJSON con = new ConversationJSON();
			con.setId(id);
			con.setBegintime(begintime);
			con.setEndtime(endtime);
			con.setKeyword(keyword);
			con.setDegree(degree);
			con.setCustomer_id(customer_id);
			con.setCustomerservice_id(customerservice_id);
			con.setFirstkeyword(firstkeyword);
			conJSONlist.add(con);
		}
		return conJSONlist;
	}
}
