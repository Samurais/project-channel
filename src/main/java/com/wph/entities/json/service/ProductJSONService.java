package com.wph.entities.json.service;

import java.util.ArrayList;
import java.util.List;

import com.wph.entities.json.ConversationJSON;
import com.wph.entities.json.ProductJSON;

public class ProductJSONService {
	public static List<ProductJSON> producttoJSON(List<Object[]> productlist) {
		List<ProductJSON> productJSONlist = new ArrayList<ProductJSON>();
		for (Object[] o : productlist) {
			Integer id = (Integer) o[0];
			Integer category_id = (Integer) o[1];
			String name = (String) o[2];
			Integer price = (Integer) o[3];
			String pic = (String) o[4];
			String remark = (String) o[5];
			String xremark = (String) o[6];
			Integer status = (Integer) o[7];
			
			ProductJSON pro = new ProductJSON();
			pro.setId(id);
			pro.setCategory_id(category_id);
			pro.setName(name);
			pro.setPic(pic);
			pro.setPrice(price);
			pro.setRemark(remark);
			pro.setStatus(status);
			pro.setXremark(xremark);
			
			productJSONlist.add(pro);
		}
		return productJSONlist;
	}
}
