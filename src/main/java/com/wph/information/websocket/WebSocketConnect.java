package com.wph.information.websocket;

import java.util.Map;

import com.wph.entities.Msg;

import net.sf.json.JSONObject;

public interface WebSocketConnect {
	//底层连接层传递消息的方法
	public void send(Msg msg, Integer statusid);
}
