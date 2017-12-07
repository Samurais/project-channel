package com.wph.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.entities.Msg;
import com.wph.entities.Msgtype;
import com.wph.entities.json.MsgJSON;
import com.wph.model.ShortMsg;

import net.sf.json.JSONObject;

public interface MsgService extends BaseService<Msg> {
	public Msg saveMsg(Customerservice customerservice,Customer customer,Msgtype msgtype,Timestamp sendtime,String content,String sensitiveword);
	public Map<String,Object> msgtoMap(Msg msg);
	public ShortMsg msgtoShortMsg(Msg msg);
	public JSONObject msgtoJson(Msg msg);
	
	public BigInteger getCount(Integer companyid);
	public List<MsgJSON> pageQuery(Integer limit, Integer offset, String search, Integer cpId);
}