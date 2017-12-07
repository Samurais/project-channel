package com.wph.service;

import java.util.List;

import com.wph.entities.Chatonline;
import com.wph.entities.ChatonlineId;
import com.wph.entities.Msg;

public interface ChatonlineService extends BaseService<Chatonline> {
	public void pushChatonline(Msg msg);
	public void popChatonline(ChatonlineId chatonlineId);
	
	//���ҿ͑�������������пͷ�
	public List<Integer> customerlogoutGetService(Integer id);
	//ɾ���͑�������������пͷ�
	public void customerlogoutDeleteService(Integer id);
	
	//���ҿͷ�������������п͑�
	public List<Integer> servicelogoutGetCustomer(Integer serviceid);
	//ɾ���ͷ�������������пͻ�
	public void servicelogoutDeleteCustomer(Integer serviceid);
}
