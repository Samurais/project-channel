package com.wph.information.websocket;

import java.util.Map;

import com.wph.entities.Msg;

import net.sf.json.JSONObject;

public interface WebSocketConnect {
	//�ײ����Ӳ㴫����Ϣ�ķ���
	public void send(Msg msg, Integer statusid);
}
