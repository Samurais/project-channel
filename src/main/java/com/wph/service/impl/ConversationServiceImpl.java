package com.wph.service.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.type.StandardBasicTypes;
import org.lionsoul.jcseg.tokenizer.core.JcsegException;
import org.springframework.stereotype.Service;

import com.wph.entities.Conversation;
import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.entities.Msg;
import com.wph.entities.json.ConversationJSON;
import com.wph.entities.json.HotWordJSON;
import com.wph.entities.json.service.ConversationJSONService;
import com.wph.model.ShortMsg;
import com.wph.service.ConversationService;
import com.wph.util.Jcseg;

@Service("conversationService")
public class ConversationServiceImpl extends BaseServiceImpl<Conversation> implements ConversationService {
	// ********************************************************************************
	// ===================================分词工具类=====================================
	@Resource
	private Jcseg jcseg;

	public Jcseg getJcseg() {
		return jcseg;
	}

	@Override
	public void beginConversation(Msg msg) {
		Customer customer = msg.getCustomer();
		Customerservice customerservice = msg.getCustomerservice();

		Conversation conversation = new Conversation();
		conversation.setCustomer(customer);
		conversation.setCustomerservice(customerservice);
		conversation.setBegintime(new Timestamp(System.currentTimeMillis()));
		getSession().save(conversation);
	}

	@Override
	public void setDegree(ShortMsg msg) {
		System.out.println("setDegree");
		String sql = "select c.id from conversation c where c.begintime=(select max(o.begintime) from conversation o  where customer_id=:ctId and costomerservice_id=:csId)";
		List<Integer> ids = getSession().createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER)
				.setInteger("ctId", msg.getCustomer_id()).setInteger("csId", msg.getCustomerservice_id()).list();
		Conversation conversation = get(ids.get(0));
		System.out.println("---------------------------------------------------");
		System.out.println(ids.get(0));
		conversation.setEndtime(new Timestamp(System.currentTimeMillis()));
		conversation.setDegree(msg.getContent());
		update(conversation);
		System.out.println("成功");
	}

	@Override
	public void setDegree(ShortMsg msg, Conversation conversation) {
		System.out.println("---------------------------------------------------");
		conversation.setEndtime(new Timestamp(System.currentTimeMillis()));
		conversation.setDegree(msg.getContent());
		update(conversation);
		System.out.println("成功");

	}

	@Override
	public void setKeyWord(ShortMsg msg) {

	}

	// 捕捉关键字
	@Override
	public void setKeyWord(ShortMsg msg, Conversation conversation) {
		System.out.println("---------------------------------------------------");
		String sql = "select m.content from msg m where m.msgtype_id=2100 "
				+ "and m.sendtime>all(select o.sendtime from msg o where msgtype_id = 3204) "
				+ "and customer_id=:customer_id and customerservice_id=:customerservice_id";
		List<String> strs = getSession().createSQLQuery(sql).addScalar("content", StandardBasicTypes.STRING)
				.setInteger("customer_id", msg.getCustomer_id())
				.setInteger("customerservice_id", msg.getCustomerservice_id()).list();
		StringBuffer sb = new StringBuffer();
		sb.append("");
		for (String str : strs) {
			sb.append(str);
		}
		List<String> keywords = null;
		try {
			keywords = jcseg.createKeywordsExtractorCOMPLEX().getKeywordsFromString(sb.toString());
		} catch (IOException | JcsegException e) {
			// TODO 自动生成的 catch 块
			System.out.println("==========分词错误=========");
			e.printStackTrace();
		}
		if (keywords.size() > 0) {
			conversation.setFirstkeyword(keywords.get(0));
			conversation.setKeyword(keywords.toString());
			update(conversation);
			System.out.println("成功");
		} else {
			System.out.println("失败");
		}

	}

	@Override
	public BigInteger getCount(Integer companyid) {
		System.out.println("Conversation:getCount");
		String sql = "select count(*) from conversation c where c.costomerService_id in(select t.cs_id from customerservice t where t.company_id=:cpId)";
		return (BigInteger) getSession().createSQLQuery(sql).setInteger("cpId", companyid).uniqueResult();
	}

	@Override
	public List<ConversationJSON> pageQuery(Integer limit, Integer offset, String search, Integer cpId) {
		System.out.println("Conversation:pageQuery");
		List<Object[]> msglist = null;
		if (search == null) {
			String sql = "select * from conversation c where c.costomerService_id in(select t.cs_id from customerservice t where t.company_id=:cpId)";
			msglist = getSession().createSQLQuery(sql).setInteger("cpId", cpId).setFirstResult(offset)
					.setMaxResults(limit).list();
		} else {
			String sql = "select * from conversation c where c.costomerservice_id in(select t.cs_id from customerservice t where t.company_id=:cpId) and c.keyword like:search";
			msglist = getSession().createSQLQuery(sql).setInteger("cpId", cpId).setString("search", "%" + search + "%")
					.setFirstResult(offset).setMaxResults(limit).list();
		}

		return ConversationJSONService.msgtoJSON(msglist);
	}

	@Override
	public Conversation getLatelyConversation(Integer customerid, Integer customerserviceid) {
		System.out.println("Conversation:getLatelyConversation");
		String sql = "select c.id from conversation c where c.begintime=(select max(o.begintime) from conversation o  where customer_id=:ctId and costomerservice_id=:csId)";
		List<Integer> ids = getSession().createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER)
				.setInteger("ctId", customerid).setInteger("csId", customerserviceid).list();
		Conversation conversation = get(ids.get(0));
		return conversation;
	}

	@Override
	public BigInteger getCountByService(Integer serviceId) {
		System.out.println("Conversation:getCountByService");
		String sql = "select count(*) from conversation c where c.costomerService_id = :serviceId";
		return (BigInteger) getSession().createSQLQuery(sql).setInteger("serviceId", serviceId).uniqueResult();
	}

	@Override
	public List<ConversationJSON> pageQueryByService(Integer limit, Integer offset, String search, Integer serviceId) {
		System.out.println("Conversation:pageQueryByService");
		List<Object[]> msglist = null;
		if (search == null) {
			String sql = "select * from conversation c where c.costomerService_id = :serviceId";
			msglist = getSession().createSQLQuery(sql).setInteger("serviceId", serviceId).setFirstResult(offset)
					.setMaxResults(limit).list();
		} else {
			String sql = "select * from conversation c where c.costomerService_id = :serviceId and c.keyword like:search";
			msglist = getSession().createSQLQuery(sql).setInteger("serviceId", serviceId)
					.setString("search", "%" + search + "%").setFirstResult(offset).setMaxResults(limit).list();
		}
		return ConversationJSONService.msgtoJSON(msglist);
	}

	@Override
	public List<HotWordJSON> getHotWord(Integer companyid) {
		String sql = "select c.keyword from conversation c where c.keyword is not null and "
				+ "c.costomerService_id in(select o.cs_id from customerservice o where o.company_id=:companyid);";
		List<String> HotWordJSON = getSession().createSQLQuery(sql).setInteger("companyid", companyid).list();
		if (HotWordJSON.size() == 0) {
			return null;
		}
		List<HotWordJSON> hotwordJSONList = new ArrayList<HotWordJSON>();
		for (String HotWord : HotWordJSON) {
			if (HotWord == null) {
				continue;
			}
			HotWord = HotWord.substring(1, HotWord.length() - 1);
			String[] strlist = HotWord.split(",");
			Boolean isnew = true;
			for (String str : strlist) {
				for (HotWordJSON hotword : hotwordJSONList) {
					if (str.equals(hotword.getName())) {
						isnew = false;
						hotword.setValue(hotword.getValue() + 1);
						break;
					}
				}
				if (isnew == true) {
					HotWordJSON newhotword = new HotWordJSON();
					newhotword.setName(str);
					newhotword.setValue(1);
					hotwordJSONList.add(newhotword);
				}
			}
		}
		Collections.sort(hotwordJSONList);
		if (hotwordJSONList.size() < 10) {
			return hotwordJSONList;

		} else {
			hotwordJSONList = hotwordJSONList.subList(hotwordJSONList.size()-9, hotwordJSONList.size());
			return hotwordJSONList;
		}
	}

	@Override
	public List<HotWordJSON> getHotWordLastMonth(Integer companyid) {
		String sql = "select c.keyword from conversation c where"
				+ " c.keyword is not null and DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= c.begintime and "
				+ "c.costomerService_id in(select o.cs_id from customerservice o where o.company_id=:companyid);";
		List<String> HotWordJSON = getSession().createSQLQuery(sql).setInteger("companyid", companyid).list();
		if (HotWordJSON.size() == 0) {
			return null;
		}
		List<HotWordJSON> hotwordJSONList = new ArrayList<HotWordJSON>();
		for (String HotWord : HotWordJSON) {
			if (HotWord == null) {
				continue;
			}
			HotWord = HotWord.substring(1, HotWord.length() - 1);
			String[] strlist = HotWord.split(",");
			Boolean isnew = true;
			for (String str : strlist) {
				for (HotWordJSON hotword : hotwordJSONList) {
					if (str.equals(hotword.getName())) {
						isnew = false;
						hotword.setValue(hotword.getValue() + 1);
						break;
					}
				}
				if (isnew == true) {
					HotWordJSON newhotword = new HotWordJSON();
					newhotword.setName(str);
					newhotword.setValue(1);
					hotwordJSONList.add(newhotword);
				}
			}
		}
		Collections.sort(hotwordJSONList);
		if (hotwordJSONList.size() < 10) {
			return hotwordJSONList;

		} else {
			hotwordJSONList = hotwordJSONList.subList(hotwordJSONList.size()-9, hotwordJSONList.size());
			return hotwordJSONList;
		}
	}

}
