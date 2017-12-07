package com.wph.service;

import com.wph.model.CustomerserviceInfo;

public interface CustomerserviceInfoService extends BaseService<CustomerserviceInfo> {
	public CustomerserviceInfo getCustomerserviceInfo(Integer customerserviceid);
}
