package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Customerservice;

public interface CustomerserviceService extends BaseService<Customerservice>{
	//idΨһ��֤
	public boolean idValid(Integer id);
	
	//��ѯ��������
	public Long getCount();
	
	//��ҳ��ѯ
	public List<Customerservice> pageQuery(Integer limit,Integer offset);
	
	//����id��ɾ��
	public void deleteByIds(String ids);
	
	//ģ����ѯ
	public List<Customerservice> pageQuery(Integer limit,Integer offset,String search);
	
	//��½��֤
	public String loginValidate(Integer id,String password);
	
	//�ͷ��༭��Ϣ
	public void editService(Customerservice service);
	

	//����˾��֤�ı��淽��
	public void save(Customerservice model, Integer cpId);

	//����˾��֤��ҳ������
	public List<Customerservice> pageQuery(Integer limit, Integer offset, String search, Integer cpId);

	//����˾��֤������ͳ��
	public BigInteger getCount(Integer cpId);

	//����˾��֤���޸�
	public void update(Customerservice model, Integer companyid);
	
	//��ù�˾�µ����пͷ�ID
	public List<Integer> getCustomerserviceIdList(Integer companyid);
	
	//��ù�˾�µ����пͷ�ID:��ҳ��ѯ���
	public List<Integer> getCustomerserviceIdList(Integer companyid,Integer limit, Integer offset);
	
	//��idsת��ΪString
	public String idListtoString(List<Integer> ids);
	
	//�鿴�ͷ��Ƿ����ڻỰ
	public Boolean isOnChat(Integer serviceid);
	
	//�鿴�ͷ��Ƿ����ڵȴ�����
	public Boolean isOnWait(Integer serviceid);
	
	//�鿴�ͷ��Ƿ�����
	public Boolean isOnline(Integer serviceid);

	//�����ӷ�ҳ��ѯ
	public List<Customerservice> pageQueryJoinRole(Integer limit, Integer offset, String search, Integer companyid);
	
}
