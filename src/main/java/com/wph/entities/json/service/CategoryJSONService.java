package com.wph.entities.json.service;

import java.util.ArrayList;
import java.util.List;

import com.wph.entities.json.CategoryJSON;
import com.wph.entities.json.ConversationJSON;

public class CategoryJSONService {
	public static List<CategoryJSON> categorytoJSON(List<Object[]> categorylist) {
		List<CategoryJSON> categoryJSONlist = new ArrayList<CategoryJSON>();
		for (Object[] o : categorylist) {
			Integer id = (Integer) o[0];
			Integer account = (Integer) o[1];
			String type = (String)o[2];
			Integer company_id = (Integer)o[3];
			String pic = (String)o[4];
			
			
			CategoryJSON category = new CategoryJSON();
			category.setId(id);
			category.setCompany_id(company_id);
			category.setAccount(account);
			category.setPic(pic);
			category.setType(type);
			
			categoryJSONlist.add(category);
		}
		return categoryJSONlist;
	}
}
