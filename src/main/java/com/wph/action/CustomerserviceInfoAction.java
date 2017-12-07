package com.wph.action;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.model.CustomerserviceInfo;

@Controller("customerserviceInfoAction")
@Scope("prototype")
public class CustomerserviceInfoAction extends BaseAction<CustomerserviceInfo>{
	public String getCustomerserviceinfo(){
		jsonMap = new HashMap<String,Object>();
		jsonMap.put("customerserviceinfo", customerserviceInfoService.getCustomerserviceInfo(model.getId()));
		return "jsonMap";
	}
}
