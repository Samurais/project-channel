package com.wph.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.entities.Company;
import com.wph.entities.Robotknowledgebase;
import com.wph.entities.json.RobotknowledgebaseJSON;
import com.wph.service.RobotknowledgebaseService;

@Service("robotknowledgebaseService")
public class RobotknowledgebaseServiceImpl extends BaseServiceImpl<Robotknowledgebase>
		implements RobotknowledgebaseService {

	@Override
	public List<RobotknowledgebaseJSON> pageQuery(Integer limit, Integer offset, String search, Integer companyid) {
		List<Object[]> oblist = null;
		List<RobotknowledgebaseJSON> jsonlist = new ArrayList<RobotknowledgebaseJSON>();
		if (search == null) {
			String sql = "select * from robotknowledgebase k where k.company_id = :companyid";
			oblist = getSession().createSQLQuery(sql).setInteger("companyid", companyid).setFirstResult(offset)
					.setMaxResults(limit).list();
		} else {

			String sql = "select * from robotknowledgebase k where k.company_id = :companyid and k.title like :search";
			oblist = getSession().createSQLQuery(sql).setInteger("companyid", companyid)
					.setString("search", "%" + search + "%").setFirstResult(offset).setMaxResults(limit).list();
		}
		for (Object[] ob : oblist) {
			RobotknowledgebaseJSON json = new RobotknowledgebaseJSON();
			Object id = ob[0];
			Object title = ob[1];
			Object category = ob[2];
			Object createtime = ob[3];
			Object content = ob[4];
			Object cpid = ob[5];

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
			if (cpid != null) {
				json.setCompanyid((Integer) cpid);
			}

			jsonlist.add(json);
		}
		return jsonlist;
	}

	@Override
	public BigInteger getCount(Integer companyid) {
		System.out.println("Robotknowledgebase:getCount");
		String sql = "select count(*) from robotknowledgebase k where k.company_id = :companyid";
		try {
			getSession().createSQLQuery(sql).setInteger("companyid", companyid).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (BigInteger) getSession().createSQLQuery(sql).setInteger("companyid", companyid).uniqueResult();
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Robotknowledgebase c where c.id in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public void saveByCompany(RobotknowledgebaseJSON json, Integer companyid) {
		Robotknowledgebase robot = new Robotknowledgebase();
		Integer id = json.getId();
		String title = json.getTitle();
		String category = json.getCategory();
		String content = json.getContent();

		if (id != null) {
			robot.setId(id);
		}
		if (title != null) {
			robot.setTitle(title);
		}
		if (category != null) {
			robot.setCategory(category);
		}
		if (content != null) {
			robot.setContent(content);
		}
		if (companyid != null) {
			robot.setCompany((Company) getSession().get(Company.class, companyid));
		}
		robot.setCreatetime(new Timestamp(System.currentTimeMillis()));

		save(robot);
	}

	@Override
	public Robotknowledgebase jsonToKnowledge(RobotknowledgebaseJSON json) {
		Robotknowledgebase base = new Robotknowledgebase();
		Integer id = json.getId();
		String title = json.getTitle();
		String category = json.getCategory();
		Integer company = json.getCompanyid();
		String content = json.getContent();

		base.setId(id);
		base.setTitle(title);
		base.setCategory(category);
		base.setContent(content);
		if (company != null) {
			base.setCompany((Company) getSession().get(Company.class, company));
		}

		return base;
	}

	@Override
	public Robotknowledgebase searchByKeyWord(String keyword) {
		String hql = "from Robotknowledgebase r where r.content like :keyword";
		List<Robotknowledgebase> list = getSession().createQuery(hql).setString("keyword", "%" + keyword + "%").list();
		if (list.size() == 0) {
			return null;
		} else {
			Integer count = list.get(0).getSearchcount();
			if(count!=null){				
				list.get(0).setSearchcount(list.get(0).getSearchcount() + 1);
			}else{
				list.get(0).setSearchcount(0);
			}
			return list.get(0);
		}
	}

}
