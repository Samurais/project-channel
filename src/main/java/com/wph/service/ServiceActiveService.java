package com.wph.service;

import com.wph.model.ServiceActive;

public interface ServiceActiveService extends BaseService<ServiceActive> {
	
	public ServiceActive getServiceActive(Integer serviceid);
}
