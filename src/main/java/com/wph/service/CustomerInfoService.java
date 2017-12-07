package com.wph.service;

import com.wph.model.CustomerInfo;

public interface CustomerInfoService extends BaseService<CustomerInfo> {
	public CustomerInfo getCustomerInfo(Integer customerid);
}
