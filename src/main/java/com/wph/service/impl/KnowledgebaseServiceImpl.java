package com.wph.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.wph.entities.Company;
import com.wph.entities.Customerservice;
import com.wph.entities.Knowledgebase;
import com.wph.entities.json.KnowledgebaseJSON;
import com.wph.service.KnowledgebaseService;

@Service("knowledgebaseService")
@Scope("prototype")
public class KnowledgebaseServiceImpl extends BaseServiceImpl<Knowledgebase> implements KnowledgebaseService {

	@Override
	public Knowledgebase jsonToKnowledgebase(KnowledgebaseJSON json) {
		Knowledgebase knowledgebase = new Knowledgebase();
		String title = json.getTitle();
		String category = json.getCategory();
		Integer company_id = json.getCompany_id();
		Integer customerservice_id = json.getCustomerservice_id();
		String content = json.getContent();
		Integer id = json.getId();

		if (title != null) {
			knowledgebase.setTitle(title);
		}
		if (category != null) {
			knowledgebase.setCategory(category);
		}
		if (company_id != null) {
			knowledgebase.setCompany((Company) getSession().get(Company.class, company_id));
		}
		if (customerservice_id != null) {
			knowledgebase
					.setCustomerservice((Customerservice) getSession().get(Customerservice.class, customerservice_id));
		}
		if (content != null) {
			knowledgebase.setContent(content);
		}
		if (id != null) {
			knowledgebase.setId(id);
		}

		return knowledgebase;
	}

	@Override
	public KnowledgebaseJSON knowledgebaseToJSON(Knowledgebase base) {
		KnowledgebaseJSON json = new KnowledgebaseJSON();
		String category = base.getCategory();
		Company company = base.getCompany();
		String content = base.getContent().toString();
		Timestamp createtime = base.getCreatetime();
		Customerservice customerservice = base.getCustomerservice();
		Integer id = base.getId();
		String title = base.getTitle();

		json.setCategory(category);
		if (company != null) {
			json.setCompany_id(company.getCpId());
		}
		json.setContent(content);
		if (createtime != null) {
			json.setCreatetime(createtime.toString());
		}
		if (customerservice != null) {
			json.setCustomerservice_id(customerservice.getCsId());
		}
		json.setId(id);
		json.setTitle(title);

		return json;
	}

	@Override
	public KnowledgebaseJSON obArrayToJSON(Object[] ob) {
		KnowledgebaseJSON json = new KnowledgebaseJSON();
		Object id = ob[0];
		Object title = ob[1];
		Object category = ob[2];
		Object createtime = ob[3];
		Object content = ob[4];
		Object company_id = ob[5];
		Object customerservice_id = ob[6];

		if (id != null) {
			json.setId((Integer) id);
		}
		if (title != null) {
			json.setTitle((String) title);
		}
		if (category != null) {
			json.setCategory((String) category);
		}
		if (createtime != null) {
			json.setCreatetime(createtime.toString());
		}
		if (content != null) {
			json.setContent((String) content);
		}
		if (company_id != null) {
			json.setCompany_id((Integer) company_id);
		}
		if (customerservice_id != null) {
			json.setCustomerservice_id((Integer) customerservice_id);
		}
		return json;
	}

	@Override
	public List<KnowledgebaseJSON> pageQuery(Integer limit, Integer offset, String search, Integer companyid) {
		List<Object[]> oblist = null;
		List<KnowledgebaseJSON> jsonlist = new ArrayList<KnowledgebaseJSON>();
		if (search == null) {
			String sql = "select * from knowledgebase k where k.company_id = :companyid";
			oblist = getSession().createSQLQuery(sql).setInteger("companyid", companyid).setFirstResult(offset)
					.setMaxResults(limit).list();
		} else {

			String sql = "select * from knowledgebase k where k.company_id = :companyid and k.title like :search";
			oblist = getSession().createSQLQuery(sql).setInteger("companyid", companyid)
					.setString("search", "%" + search + "%").setFirstResult(offset).setMaxResults(limit).list();
		}
		for (Object[] ob : oblist) {
			jsonlist.add(obArrayToJSON(ob));
		}
		return jsonlist;
	}

	@Override
	public BigInteger getCount(Integer companyid) {
		System.out.println("Knowledgebase:getCount");
		String sql = "select count(*) from knowledgebase k where k.company_id = :companyid";
		return (BigInteger) getSession().createSQLQuery(sql).setInteger("companyid", companyid).uniqueResult();
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Knowledgebase c where c.id in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();
	}

}
