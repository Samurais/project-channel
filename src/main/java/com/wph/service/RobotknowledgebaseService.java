package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Robotknowledgebase;
import com.wph.entities.json.RobotknowledgebaseJSON;

public interface RobotknowledgebaseService extends BaseService<Robotknowledgebase> {
	// 带公司验证的页面请求
	public List<RobotknowledgebaseJSON> pageQuery(Integer limit, Integer offset, String search, Integer companyid);

	// 带公司验证的数量统计
	public BigInteger getCount(Integer companyid);

	// 批量删除
	public void deleteByIds(String ids);
	
	public void saveByCompany(RobotknowledgebaseJSON json,Integer companyid);
	
	public Robotknowledgebase jsonToKnowledge(RobotknowledgebaseJSON json);
	
	public Robotknowledgebase searchByKeyWord(String keyword);
}
