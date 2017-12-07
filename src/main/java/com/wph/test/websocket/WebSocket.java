package com.wph.test.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wph.util.impl.JsonUtils;
//**����������session�ͱ���session�Ļ���
//**�յ����websocket��ʱ���������ôЭ��
//@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
public class WebSocket {
	private WebSocketService webSocketService;
	private Session session;
	private HttpSession httpSession;
	private static Set<Session> sessions= new HashSet<>();
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws IOException, InterruptedException {
		System.out.println(session);
		this.session = session;
		sessions.add(session);
		this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		if (httpSession != null) {
			ApplicationContext ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(httpSession.getServletContext());
			this.webSocketService = (WebSocketService) ctx.getBean("webSocketService");
		}
	}

	@OnClose
	public void onClose() throws IOException, InterruptedException {
		System.out.println(session.getRequestURI()+"�ر�����");
		sessions.remove(session);
	}

	@OnMessage
	public void onMessage(String message, EndpointConfig config) throws IOException, InterruptedException {
		System.out.println(message);
		Map msgMap = JsonUtils.jsonToMap(message);
		if("2".equals(msgMap.get("msgType"))){
			session.getBasicRemote().sendText("2");
			session.close();
			return;
		};
		System.out.println("session ����:");
		//sessions = session.getOpenSessions();
		System.out.println(sessions.size());
		for (Session session2 : sessions) {
			System.out.println(session2);
			//session2.getBasicRemote().sendText((String) msgMap.get("msgContent"));
		}
		
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
	}
	
	
	public WebSocket(){
		super();
		System.out.println("WebSocket����");
	}
	
	protected void finalize( )
	{
		System.out.println("WebSocket������");
	}
	
}
