package com.wph.information.servlet;

import java.util.Map;

import javax.websocket.Session;

import com.wph.connect.WebConnect;
import com.wph.entities.Msg;
import com.wph.entities.Terminal;

import net.sf.json.JSONObject;

public interface ServletPacking {
	public void getMsg(String message, WebConnect webConnect);

	public Msg msgPacking(ReceiveXmlEntity xmlEntity);

	public void saveMsg(Msg msg);

	public void msgFilter(Msg msg);

	public void sendMsg(Msg msg, Terminal terminal);
	
	public void submitMsg(ReceiveXmlEntity xmlEntity);
}
