package com.wph.service;

import java.util.List;

import com.wph.model.CompanyMonitor;

public interface CompanyMonitorService extends BaseService<CompanyMonitor>{
	public CompanyMonitor getCompanyMonitor(List<Integer> ids, Integer companyid);
}
