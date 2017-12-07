package com.wph.service;

import com.wph.entities.Customerservice;
import com.wph.entities.Waitserviceonline;

public interface WaitserviceonlineService extends BaseService<Waitserviceonline> {
	
	//1.����Ƿ��п��пͷ�
	public Boolean isFree();
	
	//1.����Ƿ��п��пͷ�
	public Boolean isFree(Integer companyid);
	
	//2.ȡ����ͷ��һ���ͷ�
	public Customerservice popCustomerService();
	
	//2.���ݹ�˾IDȡ����ͷ�ͷ�
	//public Customerservice popCustomerService(Integer companyid);
	//2.��Ϊ�����ص����⻹��Ҫһ��ֱ�ӻ�ȡID�ķ���
	public Integer popCustomerService(Integer companyid);
	
	
	//3.�ͷ��������
	public void pushCustomerService(Customerservice customerservice);
	
	//�ͷ���½��Ϣ����
	public void serviceConnectWebSocket(Customerservice customerservice);
	
	//�ͷ��ǳ�
	public void servicelogout(Integer id);
}
