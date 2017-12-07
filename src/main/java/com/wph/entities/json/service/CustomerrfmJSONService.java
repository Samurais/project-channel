package com.wph.entities.json.service;

import java.util.ArrayList;
import java.util.List;

import com.wph.entities.json.ConversationJSON;
import com.wph.entities.json.CustomerrfmJSON;

public class CustomerrfmJSONService {
	public static List<CustomerrfmJSON> customerrfmtoJSON(List<Object[]> rfmlist) {
		List<CustomerrfmJSON> rfmJSONlist = new ArrayList<CustomerrfmJSON>();
		for (Object[] o : rfmlist) {
			Integer id = (Integer) o[0];
			Integer customer_id = (Integer)o[1];
			String lastbuytime = null;
			if (o[2] != null) {
				lastbuytime = (String) o[2].toString();
			}
			Integer monthbuytimes = (Integer)o[3];
			Integer lastmonthbuytimes = (Integer)o[4];
			Integer monthbuysum = (Integer)o[5];
			Integer lastmonthbuysum = (Integer)o[6];
			
			
			CustomerrfmJSON rfm = new CustomerrfmJSON();
			rfm.setId(id);
			rfm.setCustomer_id(customer_id);
			rfm.setLastbuytime(lastbuytime);
			rfm.setLastmonthbuysum(lastmonthbuysum);
			rfm.setLastmonthbuytimes(lastmonthbuytimes);
			rfm.setMonthbuysum(monthbuysum);
			rfm.setMonthbuytimes(monthbuytimes);
			
			rfmJSONlist.add(rfm);
		}
		return rfmJSONlist;
	}
}
