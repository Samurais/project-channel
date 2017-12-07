package com.wph.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.lionsoul.jcseg.tokenizer.core.JcsegException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Knowledgebase;
import com.wph.entities.json.KnowledgebaseJSON;
import com.wph.lucene.IndexDao;
import com.wph.lucene.QueryResult;

@Controller("knowledgebaseAction")
@Scope("prototype")
public class KnowledgebaseAction extends BaseAction<KnowledgebaseJSON> {

	@Resource
	private IndexDao indexDao;

	public void setIndexDao(IndexDao indexDao) {
		this.indexDao = indexDao;
	}

	// ********************************************************************************
	// ===============================Lucene��ѯ========================================
	private String queryString;
	private int firstResult;
	private int maxResult;

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	// ********************************************************************************
	// ===============================��������===========================================
	public String saveByCompany() {
		String path = companyService.getBasePath(model.getCompany_id());
		Knowledgebase base = knowledgebaseService.jsonToKnowledgebase(model);
		knowledgebaseService.save(base);
		Integer id = base.getId();
		model.setId(id);
		indexDao.saveByCompany(model, path);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==================================��˾�ķִʷ���===================================
	public String jcsegSub() {
		jsonList = new ArrayList<String>();
		try {
			jsonList = jcseg.createKeywordsExtractorCOMPLEX().getKeywordsFromFile(queryString);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (JcsegException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return "jsonList";
	}

	// ********************************************************************************
	// ===============================��˾��Lucene��ѯ===================================
	public String searchByCompany() {
		jsonMap = new HashMap<String, Object>();
		String path = companyService.getBasePath(model.getCompany_id());
		QueryResult result = indexDao.searchByCompany(queryString, firstResult, maxResult, path);
		jsonMap.put("total", result.getCount());
		jsonMap.put("rows", result.getList());
		return "jsonMap";
	}

	// ********************************************************************************
	// ===============================��������===========================================
	public String updateByCompany() {
		String path = companyService.getBasePath(model.getCompany_id());
		Knowledgebase base = knowledgebaseService.jsonToKnowledgebase(model);
		knowledgebaseService.update(base);
		indexDao.updateByCompany(base, path);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ===============================����ɾ��===========================================
	public String deleteByCompany() {
		String path = companyService.getBasePath(model.getCompany_id());
		Knowledgebase base = knowledgebaseService.jsonToKnowledgebase(model);
		knowledgebaseService.deleteByIds(ids);
		String[] strarry = ids.split(",");
		if (strarry.length > 0) {
			for (String id : strarry) {
				indexDao.deleteByCompany(id, path);
			}
		}
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ===============================���ݿ��ҳ��ѯ======================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", knowledgebaseService.getCount(model.getCompany_id()));
		jsonMap.put("rows", knowledgebaseService.pageQuery(limit, offset, search, model.getCompany_id()));
		return "jsonMap";
	}

}
