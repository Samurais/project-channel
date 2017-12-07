package com.wph.information.websocket;

import java.util.Map;

import javax.websocket.Session;

import com.wph.entities.Msg;

import net.sf.json.JSONObject;

public interface WebSocketConnectService {
	// 1.��Ϣ�ĳ�������
	public void responseMsg(Msg responsemsg);

	// 3.�ͻ���Ϣ�ĳ�����װ
	public void customerSave(Map<String, Object> map);

	// 5.��Ϣ�Ľ���
	public JSONObject msgDavanning(Msg responsemsg);
	
	//session��
	public void sessionBinding(Integer bingdingId,Session session);
	
	//session���
	public void sessionUnBinding(Integer bingdingId);
	
	//��Ϣ����
	public void send(Msg msg,Integer statusid);
}
