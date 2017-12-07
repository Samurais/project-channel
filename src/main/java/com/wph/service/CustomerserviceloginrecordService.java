package com.wph.service;

import javax.servlet.http.HttpServletRequest;

import com.wph.entities.Customerserviceloginrecord;

public interface CustomerserviceloginrecordService extends BaseService<Customerserviceloginrecord> {
	public void saveCustomerserviceloginrecord(HttpServletRequest request, Integer loginid);

}
