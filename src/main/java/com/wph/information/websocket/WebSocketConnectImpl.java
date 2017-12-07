package com.wph.information.websocket;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.connect.GetHttpSessionConfigurator;
import com.wph.connect.WebConnectImpl;
import com.wph.connect.service.WebConnectService;
import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.entities.Msg;
import com.wph.entities.Msgtype;
import com.wph.util.impl.SpringUtils;

import net.sf.json.JSONObject;

@Controller("webSocketConnect")
@ServerEndpoint(value = "/websocket/{param}", configurator = GetHttpSessionConfigurator.class)
public class WebSocketConnectImpl implements WebSocketConnect {
	// ***********************************************************************************
		// =====================================日志对象========================================
		private static Logger logger = Logger.getLogger(WebConnectImpl.class);
		

		@OnOpen
		public void onOpen(@PathParam(value="param")String param,Session session, EndpointConfig config) throws IOException, InterruptedException {
			System.out.println("我的参数是" + param);
			try {
				WebSocketConnectService webSocketConnectService = (WebSocketConnectService) SpringUtils.getBean("webSocketConnectService");
				webSocketConnectService.sessionBinding(Integer.parseInt(param),session);
			} catch (Exception e) {
				
			}

		}

		@OnClose
		public void onClose(@PathParam(value="param")String param) throws IOException, InterruptedException {
			System.out.println(param);
			WebSocketConnectService webSocketConnectService = (WebSocketConnectService) SpringUtils.getBean("webSocketConnectService");
			webSocketConnectService.sessionUnBinding(Integer.parseInt(param));
			logger.info("websocket关闭连接");
		}

		@OnMessage
		public void onMessage(String message, EndpointConfig config) throws IOException, InterruptedException {
			logger.info("===========================收到消息=================================");
			logger.info(message);
			WebSocketPacking webSocketPacking = (WebSocketPacking) SpringUtils.getBean("webSocketPacking");
			webSocketPacking.submitMsg(message);
		}

		@OnError
		public void onError(@PathParam(value="param")String param,Throwable t) throws Throwable {
			
		}

		@Override
		public void send(Msg msg, Integer statusid) {
			WebSocketConnectService webSocketConnectService = (WebSocketConnectService) SpringUtils.getBean("webSocketConnectService");
			webSocketConnectService.send(msg, statusid);
		}

}
