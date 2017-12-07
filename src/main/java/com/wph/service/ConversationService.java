package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Conversation;
import com.wph.entities.Msg;
import com.wph.entities.json.ConversationJSON;
import com.wph.entities.json.HotWordJSON;
import com.wph.model.ShortMsg;

public interface ConversationService extends BaseService<Conversation> {
	public void beginConversation(Msg msg);
	public void setDegree(ShortMsg msg);
	public void setDegree(ShortMsg msg,Conversation conversation);
	public void setKeyWord(ShortMsg msg);
	public void setKeyWord(ShortMsg msg,Conversation conversation);
	
	public Conversation getLatelyConversation(Integer customerid,Integer customerserviceid);
	
	public BigInteger getCount(Integer companyid);
	public List<ConversationJSON> pageQuery(Integer limit, Integer offset, String search, Integer cpId);

	public BigInteger getCountByService(Integer serviceId);
	public List<ConversationJSON> pageQueryByService(Integer limit, Integer offset, String search,Integer serviceId);

	public List<HotWordJSON> getHotWord(Integer companyid);
	public List<HotWordJSON> getHotWordLastMonth(Integer companyid);
}
