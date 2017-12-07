package com.wph.service;

import java.util.List;

import com.wph.entities.Chatonline;
import com.wph.entities.ChatonlineId;
import com.wph.entities.Msg;

public interface ChatonlineService extends BaseService<Chatonline> {
	public void pushChatonline(Msg msg);
	public void popChatonline(ChatonlineId chatonlineId);
	
	//查找客戶在线聊天的所有客服
	public List<Integer> customerlogoutGetService(Integer id);
	//删除客戶在线聊天的所有客服
	public void customerlogoutDeleteService(Integer id);
	
	//查找客服在线聊天的所有客戶
	public List<Integer> servicelogoutGetCustomer(Integer serviceid);
	//删除客服在线聊天的所有客户
	public void servicelogoutDeleteCustomer(Integer serviceid);
}
