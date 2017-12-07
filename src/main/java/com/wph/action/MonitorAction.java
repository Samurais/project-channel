package com.wph.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Company;
import com.wph.model.CompanyMonitor;
import com.wph.model.ServiceActive;

//此时这里的model是Company对象,传入的是Company的cpId
@Controller("monitorAction")
@Scope("prototype")
public class MonitorAction extends BaseAction<Company> {

	//获得公司当天的数据
	public String getCompanyMonitor() {
		jsonMap = new HashMap<String,Object>();
		Integer companyid = model.getCpId();
		List<Integer> customerserviceids = customerserviceService.getCustomerserviceIdList(companyid);
		CompanyMonitor monitor = companyMonitorService.getCompanyMonitor(customerserviceids,companyid);
		jsonMap.put("date", monitor);
		return "jsonMap";
	}
	
	// 获得客服的会话状态
	public String getCustomerState(){
		jsonMap = new HashMap<String,Object>();
		List<Integer> customerserviceids = customerserviceService.getCustomerserviceIdList(model.getCpId(),limit,offset);
		CompanyMonitor companymonitor = new CompanyMonitor();
		List<ServiceActive> serviceactivelist = new ArrayList<ServiceActive>();
		for (Integer id : customerserviceids) {
			serviceactivelist.add(serviceActiveService.getServiceActive(id));
		}
		companymonitor.setServiceactivelist(serviceactivelist);
		jsonMap.put("total", customerserviceService.getCount(model.getCpId()));
		jsonMap.put("rows", serviceactivelist);
		return "jsonMap";
	}
	
	
}
