package com.wph.information.websocket;

import com.wph.connect.WebConnect;
import com.wph.entities.Msg;
import com.wph.entities.Terminal;

public interface WebSocketPacking {
	public void getMsg(String message, WebConnect webConnect);

	public Msg msgPacking(String message);

	public void saveMsg(Msg msg);

	public void msgFilter(Msg msg);

	public void sendMsg(Msg msg, Terminal terminal);
	
	public void submitMsg(String message);
}
