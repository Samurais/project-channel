package com.wph.lucene;

import com.wph.entities.Knowledgebase;
import com.wph.entities.json.KnowledgebaseJSON;

public interface IndexDao {
	public void save(Knowledgebase know);

	public void saveByCompany(KnowledgebaseJSON json,String path);
	
	public void delete(String id);
	
	public void deleteByCompany(String id,String path);
	
	public void update(Knowledgebase know);
	
	public void updateByCompany(Knowledgebase know,String path);
	
	public QueryResult search(String queryString, int firstResult, int maxResult);
	
	public QueryResult searchByCompany(String queryString, int firstResult, int maxResult,String companypath);
}
