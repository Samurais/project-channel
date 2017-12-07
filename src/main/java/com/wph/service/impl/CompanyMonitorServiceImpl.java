package com.wph.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wph.model.CompanyMonitor;
import com.wph.model.ServiceActive;
import com.wph.service.CompanyMonitorService;
import com.wph.service.CustomerserviceService;
import com.wph.service.ServiceActiveService;

@Service("companyMonitorService")
public class CompanyMonitorServiceImpl extends BaseServiceImpl<CompanyMonitor> implements CompanyMonitorService {

	@Resource
	private CustomerserviceService customerserviceService;

	public void setCustomerserviceService(CustomerserviceService customerserviceService) {
		this.customerserviceService = customerserviceService;
	}

	// ��������ļ��е�����ȱ�����
	private Integer degreedeadline;

	@Value("#{prop.degreedeadline}")
	public void setDegreedeadline(Integer degreedeadline) {
		this.degreedeadline = degreedeadline;
	}

	// ��������ļ��е�������ܷ�
	private Integer degreetotal;

	@Value("#{prop.degreetotal}")
	public void setDegreetotal(Integer degreetotal) {
		this.degreetotal = degreetotal;
	}

	@Override
	public CompanyMonitor getCompanyMonitor(List<Integer> ids, Integer companyid) {
		CompanyMonitor companymonitor = new CompanyMonitor();

		List<ServiceActive> serviceactivelist = new ArrayList<ServiceActive>();
		// �����ɵĻỰ��
		String strids = customerserviceService.idListtoString(ids);
		String sql1 = "select count(*) from conversation c where to_days(c.begintime) = to_days(now()) and c.costomerservice_id in ("
				+ strids + ")";
		Object haveservice = getSession().createSQLQuery(sql1).uniqueResult();
		if (haveservice != null) {
			companymonitor.setConversation(((BigInteger) haveservice).intValue());
		} else {
			companymonitor.setConversation(0);
		}

		// ��������
		String sql2 = "select avg(c.degree) from conversation c where to_days(c.begintime) = to_days(now()) and c.costomerservice_id in ("
				+ strids + ") and c.degree is not null";
		Double degree = null;
		degree = (Double) getSession().createSQLQuery(sql2).uniqueResult();
		// �����7���ܵ�����,������Ը��������ļ���
		companymonitor.setDegree((degree.intValue()*100) / degreetotal);

		// �������ñ���ָ��Ϊ3���ڿ��Ը���
		String sql3 = "select count(c.degree) from conversation c where to_days(c.begintime) = to_days(now()) and c.costomerservice_id in ("
				+ strids + ") and c.degree!=null and c.degree < :degreedeadline";
		Object danger = getSession().createSQLQuery(sql3).setInteger("degreedeadline", degreedeadline).uniqueResult();
		if (danger != null) {
			companymonitor.setDanger(((BigInteger) danger).intValue());
		} else {
			companymonitor.setDanger(0);
		}

		// ��������ͻ�
		String sql4 = "select count(*) from customer c where to_days(c.ct_registtime) = to_days(now()) and c.company_id = :companyid";
		Object newcustomer = getSession().createSQLQuery(sql4).setInteger("companyid", companyid).uniqueResult();
		if (newcustomer != null) {
			companymonitor.setNewcustomer(((BigInteger) newcustomer).intValue());
		} else {
			companymonitor.setNewcustomer(0);
		}
		return companymonitor;
	}

}
