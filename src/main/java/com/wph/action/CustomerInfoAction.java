package com.wph.action;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.model.CustomerInfo;

@Controller("customerInfoAction")
@Scope("prototype")
public class CustomerInfoAction extends BaseAction<CustomerInfo> {
	public String getCustomerinfo(){
		jsonMap = new HashMap<String,Object>();
		jsonMap.put("customerinfo", customerInfoService.getCustomerInfo(model.getId()));
		return "jsonMap";
	}
	
}
