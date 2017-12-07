package com.wph.connect.service;

import com.wph.connect.WebConnect;
import com.wph.entities.Msg;
import com.wph.entities.Terminal;

/**
 * 
 * @author wu 多渠道智能整合 服务层 负责将消息整合成统一格式,向上提交,同时进行初步的过滤和客户消息封装
 *
 *         消息的格式,为数据库实体类 class Msg private Integer id; private Customerservice
 *         customerservice; private Customer customer; private Msgtype msgtype;
 *         private Timestamp sendtime; private String content; private String
 *         sensitiveword;
 */
public interface WebConnectService {

	public void getMsg(String message, WebConnect webConnect);

	public Msg msgPacking(String message);

	public void saveMsg(Msg msg);

	public void msgFilter(Msg msg);
	
	public void sendMsg(Msg msg,Terminal terminal);
	
}
