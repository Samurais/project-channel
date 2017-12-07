package com.wph.service;

import java.sql.Timestamp;
import java.util.List;

import com.wph.entities.Customer;
import com.wph.entities.Waitonline;
import com.wph.model.CustomerWaitModel;

public interface WaitonlineService extends BaseService<Waitonline> {
	
	//û�пͷ�λ��,������
	public void pushCustomer(Integer customerid);
	
	//����˾ID������
	public void pushCustomer(Integer customerid,Integer companyid);
	
	//�ͷ�����һλ��������
	public Customer popCustomer();
	
	//��һ���ͻ�
	//public Customer popCustomer(Integer companyid);
	
	public Integer popCustomer(Integer companyid);
	//�ͻ��ǳ�
	public void customerlogout(Integer id);
	
	//�ж϶����Ƿ�����
	public Boolean isFree();
	
	//�ж��ŶӶ����Ƿ�����
	public Boolean isFree(Integer companyid);
	
	//�ж϶������Ƿ��пͻ�
	public Boolean isEmpty();
	
	//�̶Զ����Ƿ��пͻ�
	public Boolean isEmpty(Integer companyid);
	
	//���ݿͻ����ȼ����������Ŷ�ʱ���㷨
	public List<CustomerWaitModel> levelTransformRealTime(List<CustomerWaitModel> cts);
	
}
