package com.wph.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wph.entities.Customerloginrecord;
import com.wph.entities.json.VisitRefererJSON;
import com.wph.entities.json.VisitRegionJSON;

public interface CustomerloginrecordService extends BaseService<Customerloginrecord> {
	
	public void saveCustomerloginrecord(HttpServletRequest request,Integer loginid);
	
	public List<VisitRegionJSON> getVisitregion(Integer companyid);
	public List<VisitRegionJSON> getVisitregionLastMonth(Integer companyid);
	
	public List<VisitRefererJSON> getVisitRefererJSON(Integer companyid);
}
