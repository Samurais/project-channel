package com.wph.service;

import com.wph.entities.Customerservice;
import com.wph.entities.Waitserviceonline;

public interface WaitserviceonlineService extends BaseService<Waitserviceonline> {
	
	//1.检查是否有空闲客服
	public Boolean isFree();
	
	//1.检查是否有空闲客服
	public Boolean isFree(Integer companyid);
	
	//2.取出队头的一个客服
	public Customerservice popCustomerService();
	
	//2.根据公司ID取出队头客服
	//public Customerservice popCustomerService(Integer companyid);
	//2.因为懒加载的问题还需要一个直接获取ID的方法
	public Integer popCustomerService(Integer companyid);
	
	
	//3.客服进入队列
	public void pushCustomerService(Customerservice customerservice);
	
	//客服登陆信息保存
	public void serviceConnectWebSocket(Customerservice customerservice);
	
	//客服登出
	public void servicelogout(Integer id);
}
