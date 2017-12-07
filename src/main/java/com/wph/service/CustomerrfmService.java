package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Customerrfm;
import com.wph.entities.Order;
import com.wph.entities.json.CustomerrfmJSON;
import com.wph.entities.json.RfmScatterJSON;

public interface CustomerrfmService extends BaseService<Customerrfm> {
	//���¿ͻ�rfm
	public void updateCustomerrfm(Integer customerid,Order order);
	//���¿ͻ����ȼ�
	public void updateCustomerlevel(Integer customerid);
	
	//��ÿͻ���RFM���ݼ�¼����
	public BigInteger getCount(Integer companyid);
	//��ÿͻ���RFM���ݼ�¼
	public List<CustomerrfmJSON> pageQuery(Integer limit, Integer offset, String search, Integer cpId);

	public List<RfmScatterJSON> getRfmScatter(Integer companyid);
	
}
