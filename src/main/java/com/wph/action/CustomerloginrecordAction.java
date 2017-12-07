package com.wph.action;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Customerloginrecord;
import com.wph.entities.json.VisitRegionJSON;

@Controller("customerloginrecordAction")
@Scope("prototype")
public class CustomerloginrecordAction extends BaseAction<Customerloginrecord>{
	
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	
	public String getVisitregion(){
		jsonList = new ArrayList<VisitRegionJSON>();	
		jsonList = customerloginrecordService.getVisitregionLastMonth(companyid);
		return "jsonList";	
	}
	
	public String getReferer(){
		jsonList = new ArrayList<VisitRegionJSON>();	
		jsonList = customerloginrecordService.getVisitRefererJSON(companyid);
		return "jsonList";
	}
}
