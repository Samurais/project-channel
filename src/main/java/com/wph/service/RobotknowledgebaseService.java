package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Robotknowledgebase;
import com.wph.entities.json.RobotknowledgebaseJSON;

public interface RobotknowledgebaseService extends BaseService<Robotknowledgebase> {
	// ����˾��֤��ҳ������
	public List<RobotknowledgebaseJSON> pageQuery(Integer limit, Integer offset, String search, Integer companyid);

	// ����˾��֤������ͳ��
	public BigInteger getCount(Integer companyid);

	// ����ɾ��
	public void deleteByIds(String ids);
	
	public void saveByCompany(RobotknowledgebaseJSON json,Integer companyid);
	
	public Robotknowledgebase jsonToKnowledge(RobotknowledgebaseJSON json);
	
	public Robotknowledgebase searchByKeyWord(String keyword);
}
