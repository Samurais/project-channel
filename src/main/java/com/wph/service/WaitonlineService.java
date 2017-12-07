package com.wph.service;

import java.sql.Timestamp;
import java.util.List;

import com.wph.entities.Customer;
import com.wph.entities.Waitonline;
import com.wph.model.CustomerWaitModel;

public interface WaitonlineService extends BaseService<Waitonline> {
	
	//没有客服位置,进队列
	public void pushCustomer(Integer customerid);
	
	//带公司ID进队列
	public void pushCustomer(Integer customerid,Integer companyid);
	
	//客服空闲一位，出队列
	public Customer popCustomer();
	
	//出一名客户
	//public Customer popCustomer(Integer companyid);
	
	public Integer popCustomer(Integer companyid);
	//客户登出
	public void customerlogout(Integer id);
	
	//判断队列是否已满
	public Boolean isFree();
	
	//判断排队队列是否已满
	public Boolean isFree(Integer companyid);
	
	//判断队列中是否有客户
	public Boolean isEmpty();
	
	//盘对队列是否有客户
	public Boolean isEmpty(Integer companyid);
	
	//根据客户优先级计算真正排队时间算法
	public List<CustomerWaitModel> levelTransformRealTime(List<CustomerWaitModel> cts);
	
}
