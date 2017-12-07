package com.wph.action;

import java.sql.Timestamp;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.model.ShortMsg;

@Controller("collaborativeFilteringAction")
@Scope("prototype")
public class CollaborativeFilteringAction extends BaseAction<ShortMsg> {
	// ***********************************************************************************
	// =====================================获得随机推荐商品的方法=============================
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public String getRandomProduct() {
		System.out.println("CollaborativeFilteringAction:getRandomProduct");
		Integer productid = productService.getRandomProductId(companyid);
		System.out.println(productid);
		jsonMap = new HashMap<String, Object>();
		ShortMsg shortMsg = null;
		if (productid == -1) {
			// 请求失败,返回客户请求的内容
			shortMsg = shortMsgService.saveShortMsg(null, null, 2311, model.getContent(),
					new Timestamp(System.currentTimeMillis()));
		} else {
			// 请求成功content 则为商品ID
			shortMsg = shortMsgService.saveShortMsg(null, null, 2310, productid.toString(),
					new Timestamp(System.currentTimeMillis()));
		}

		jsonMap = shortMsgService.msgtoMap(shortMsg);
		return "jsonMap";
	}

	// ********************************************************************************
	// =====================================获得推荐商品的方法=============================
	public String getRecommendProduct() {
		System.out.println("CollaborativeFilteringAction:getRecommendProduct");
		Integer customerid = model.getCustomer_id();
		Integer companyid = customerService.getCompanyid(customerid);
		Integer productid = productService.getRecommandProductId2(customerid, companyid);
		System.out.println(productid);
		jsonMap = new HashMap<String, Object>();
		ShortMsg shortMsg = null;
		if (productid == -1) {
			// 请求失败,返回客户请求的内容
			shortMsg = shortMsgService.saveShortMsg(customerid, null, 2311, model.getContent(),
					new Timestamp(System.currentTimeMillis()));
		} else {
			// 请求成功content 则为商品ID
			shortMsg = shortMsgService.saveShortMsg(customerid, null, 2310, productid.toString(),
					new Timestamp(System.currentTimeMillis()));
		}

		jsonMap = shortMsgService.msgtoMap(shortMsg);
		return "jsonMap";
	}

	// ********************************************************************************
	// ===================================客服获得推荐商品的方法=============================
	public String getRecommendProductByService() {
		System.out.println("CollaborativeFilteringAction:getRecommendProductByService");
		Integer customerid = model.getCustomer_id();
		Integer companyid = customerService.getCompanyid(customerid);
		Integer productid = productService.getRecommandProductId2(customerid, companyid);
		System.out.println(productid);
		jsonMap = new HashMap<String, Object>();
		ShortMsg shortMsg = null;
		if (productid == -1) {
			// 请求失败,返回客户请求的内容
			shortMsg = shortMsgService.saveShortMsg(customerid, null, 1311, model.getContent(),
					new Timestamp(System.currentTimeMillis()));
		} else {
			// 请求成功content 则为商品ID
			shortMsg = shortMsgService.saveShortMsg(customerid, null, 1310, productid.toString(),
					new Timestamp(System.currentTimeMillis()));
		}

		jsonMap = shortMsgService.msgtoMap(shortMsg);
		return "jsonMap";
	}
}
