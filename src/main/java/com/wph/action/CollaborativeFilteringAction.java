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
	// =====================================�������Ƽ���Ʒ�ķ���=============================
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
			// ����ʧ��,���ؿͻ����������
			shortMsg = shortMsgService.saveShortMsg(null, null, 2311, model.getContent(),
					new Timestamp(System.currentTimeMillis()));
		} else {
			// ����ɹ�content ��Ϊ��ƷID
			shortMsg = shortMsgService.saveShortMsg(null, null, 2310, productid.toString(),
					new Timestamp(System.currentTimeMillis()));
		}

		jsonMap = shortMsgService.msgtoMap(shortMsg);
		return "jsonMap";
	}

	// ********************************************************************************
	// =====================================����Ƽ���Ʒ�ķ���=============================
	public String getRecommendProduct() {
		System.out.println("CollaborativeFilteringAction:getRecommendProduct");
		Integer customerid = model.getCustomer_id();
		Integer companyid = customerService.getCompanyid(customerid);
		Integer productid = productService.getRecommandProductId2(customerid, companyid);
		System.out.println(productid);
		jsonMap = new HashMap<String, Object>();
		ShortMsg shortMsg = null;
		if (productid == -1) {
			// ����ʧ��,���ؿͻ����������
			shortMsg = shortMsgService.saveShortMsg(customerid, null, 2311, model.getContent(),
					new Timestamp(System.currentTimeMillis()));
		} else {
			// ����ɹ�content ��Ϊ��ƷID
			shortMsg = shortMsgService.saveShortMsg(customerid, null, 2310, productid.toString(),
					new Timestamp(System.currentTimeMillis()));
		}

		jsonMap = shortMsgService.msgtoMap(shortMsg);
		return "jsonMap";
	}

	// ********************************************************************************
	// ===================================�ͷ�����Ƽ���Ʒ�ķ���=============================
	public String getRecommendProductByService() {
		System.out.println("CollaborativeFilteringAction:getRecommendProductByService");
		Integer customerid = model.getCustomer_id();
		Integer companyid = customerService.getCompanyid(customerid);
		Integer productid = productService.getRecommandProductId2(customerid, companyid);
		System.out.println(productid);
		jsonMap = new HashMap<String, Object>();
		ShortMsg shortMsg = null;
		if (productid == -1) {
			// ����ʧ��,���ؿͻ����������
			shortMsg = shortMsgService.saveShortMsg(customerid, null, 1311, model.getContent(),
					new Timestamp(System.currentTimeMillis()));
		} else {
			// ����ɹ�content ��Ϊ��ƷID
			shortMsg = shortMsgService.saveShortMsg(customerid, null, 1310, productid.toString(),
					new Timestamp(System.currentTimeMillis()));
		}

		jsonMap = shortMsgService.msgtoMap(shortMsg);
		return "jsonMap";
	}
}
