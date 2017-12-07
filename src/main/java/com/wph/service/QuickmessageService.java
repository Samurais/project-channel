package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Quickmessage;
import com.wph.entities.Robotknowledgebase;
import com.wph.entities.json.RobotknowledgebaseJSON;

public interface QuickmessageService extends BaseService<Quickmessage> {
	public List<Quickmessage> pageQuery(Integer limit, Integer offset, String search, Integer serviceid);

	public Long getCount(Integer serviceid);

	public void deleteByIds(String ids);

}
