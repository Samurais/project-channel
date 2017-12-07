package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Customer;
import com.wph.entities.Knowledgebase;
import com.wph.entities.json.KnowledgebaseJSON;

public interface KnowledgebaseService extends BaseService<Knowledgebase> {

	public Knowledgebase jsonToKnowledgebase(KnowledgebaseJSON json);

	public KnowledgebaseJSON knowledgebaseToJSON(Knowledgebase base);

	public KnowledgebaseJSON obArrayToJSON(Object[] ob);

	// 带公司验证的页面请求
	public List<KnowledgebaseJSON> pageQuery(Integer limit, Integer offset, String search, Integer companyid);

	// 带公司验证的数量统计
	public BigInteger getCount(Integer companyid);

	// 批量删除
	public void deleteByIds(String ids);

}
