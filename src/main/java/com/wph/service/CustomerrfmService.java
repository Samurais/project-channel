package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Customerrfm;
import com.wph.entities.Order;
import com.wph.entities.json.CustomerrfmJSON;
import com.wph.entities.json.RfmScatterJSON;

public interface CustomerrfmService extends BaseService<Customerrfm> {
	//更新客户rfm
	public void updateCustomerrfm(Integer customerid,Order order);
	//更新客户优先级
	public void updateCustomerlevel(Integer customerid);
	
	//获得客户的RFM数据记录数量
	public BigInteger getCount(Integer companyid);
	//获得客户的RFM数据记录
	public List<CustomerrfmJSON> pageQuery(Integer limit, Integer offset, String search, Integer cpId);

	public List<RfmScatterJSON> getRfmScatter(Integer companyid);
	
}
