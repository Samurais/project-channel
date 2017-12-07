package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Customer;
import com.wph.entities.Customerservice;

public interface CustomerService extends BaseService<Customer> {
	// ��ѯ�Ƿ��пͷ�λ��,������Ϣ����ʱ����֤
	// idΨһ��֤
	public boolean idValid(Integer id);
	
	// ��ѯ��������
	public Long getCount();
	
	// ��ҳ��ѯ
	public List<Customer> pageQuery(Integer limit, Integer offset);
	
	// ����id��ɾ��
	public void deleteByIds(String ids);
	
	// ģ����ѯ
	public List<Customer> pageQuery(Integer limit, Integer offset, String search);
	
	//��½��֤
	public String loginValidate(Integer id, String password);

	//����˾�ĵ�½��֤
	public String loginValidate(Integer loginid, String loginpassword, Integer companyid);
	
	//����˾��֤�ı��淽��
	public void save(Customer model, Integer cpId);

	//����˾��֤��ҳ������
	public List<Customer> pageQuery(Integer limit, Integer offset, String search, Integer cpId);

	//����˾��֤������ͳ��
	public BigInteger getCount(Integer cpId);

	//����˾��֤���޸�
	public void update(Customer model, Integer companyid);
	
	//��ÿͻ��Ĺ�˾ID
	public Integer getCompanyid(Integer customerid);
}
