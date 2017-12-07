package com.wph.information.websocket;

import java.util.Map;

import javax.websocket.Session;

import com.wph.entities.Msg;

import net.sf.json.JSONObject;

public interface WebSocketConnectService {
	// 1.消息的初步过滤
	public void responseMsg(Msg responsemsg);

	// 3.客户信息的初步封装
	public void customerSave(Map<String, Object> map);

	// 5.信息的解析
	public JSONObject msgDavanning(Msg responsemsg);
	
	//session绑定
	public void sessionBinding(Integer bingdingId,Session session);
	
	//session解绑
	public void sessionUnBinding(Integer bingdingId);
	
	//消息发送
	public void send(Msg msg,Integer statusid);
}
