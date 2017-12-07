package com.wph.service;

import java.sql.Timestamp;
import java.util.Map;

import com.wph.entities.Msg;
import com.wph.model.ShortMsg;

public interface ShortMsgService extends BaseService<ShortMsg> {
	public Map<String, Object> msgtoMap(ShortMsg msg);

	public ShortMsg saveShortMsg(Integer customer_id, Integer customerservice_id, Integer msgtype_id, String content,
			Timestamp time);

}
