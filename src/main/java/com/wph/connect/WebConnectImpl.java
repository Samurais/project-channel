package com.wph.connect;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.jboss.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wph.connect.service.WebConnectService;
import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.entities.Msg;
import com.wph.entities.Msgtype;

import net.sf.json.JSONObject;

@Service("webConnect")
@ServerEndpoint(value = "/websocket/web", configurator = GetHttpSessionConfigurator.class)
public class WebConnectImpl implements WebConnect {
	// ***********************************************************************************
	// =====================================日志对象========================================
	private static Logger logger = Logger.getLogger(WebConnectImpl.class);
	// ***********************************************************************************
	// =====================================所有对象========================================
	private Session session;
	private HttpSession httpSession;
	private Integer id;
	private WebConnectService webConnectService;
	private static Map<Integer, Session> sessions = new HashMap<Integer, Session>();
	// ***********************************************************************************
	// ==============================Service服务对象========================================

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws IOException, InterruptedException {
		logger.info("websocket收到连接 :" + session);
		this.session = session;
		this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		if (httpSession != null) {
			// ApplicationContext ctx = WebApplicationContextUtils
			// .getRequiredWebApplicationContext(httpSession.getServletContext());
			try {
				ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");
				this.webConnectService = (WebConnectService) ctx.getBean("webConnectService");
			} catch (Exception e) {
				logger.error("第一层:获得bean失败");
			}
		} else {
			logger.error("httpSession = null");
		}

	}

	@OnClose
	public void onClose() throws IOException, InterruptedException {
		if (sessions.get(id) != null) {
			sessions.remove(id);
		}
		logger.info("websocket关闭连接");
	}

	@OnMessage
	public void onMessage(String message, EndpointConfig config) throws IOException, InterruptedException {
		logger.info("===========================收到消息=================================");
		logger.info(message);
		webConnectService.getMsg(message, this);
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
	}

	// ***********************************************************************************
	// =====================================重写的方法======================================
	/**
	 * 接受上层传下来的消息 1.解析Msg为String类型
	 */
	@Override
	public JSONObject msgDavanning(Msg responsemsg) {
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		// 消息的四个字段:1.客户id 2.客服id 3.消息类型 4.发送时间5.内容
		Customerservice customerservice = responsemsg.getCustomerservice();
		Customer customer = responsemsg.getCustomer();
		Msgtype msgtype = responsemsg.getMsgtype();
		Timestamp sendtime = responsemsg.getSendtime();
		String content = responsemsg.getContent();
		if (customerservice != null) {
			jsonmap.put("customerservice_id", customerservice.getCsId());
		}
		if (customer != null) {
			jsonmap.put("customer_id", customer.getCtId());
		}
		if (msgtype != null) {
			jsonmap.put("msgtype_id", msgtype.getId());
		}
		if (sendtime != null) {
			jsonmap.put("sendtime", sendtime.toString());
		}
		if (content != null) {
			jsonmap.put("content", content);
		}
		JSONObject jsonObject = JSONObject.fromObject(jsonmap);
		return jsonObject;
	}

	/**
	 * 返回在服务层即第二层过滤的消息 2.将解析好的消息发送 1).解析消息类型 2).提取要发送的id 3).根据id获得sessions对象
	 * 4).消息发送
	 */
	@Override
	public void responseMsg(Msg responsemsg) {
		Integer msgtype = (responsemsg.getMsgtype().getId()) % 1000;
		Integer sendId = null;
		Session sendSession = null;
		if ((msgtype >= 100) && (msgtype < 200)) {
			// 返回给客服
			sendId = responsemsg.getCustomerservice().getCsId();
		} else if ((msgtype >= 200) && (msgtype < 300)) {
			// 返回给客户
			sendId = responsemsg.getCustomer().getCtId();
		} else {
			logger.error("第一层:msgtype Error");
			return;
		}
		JSONObject message = msgDavanning(responsemsg);
		sendSession = sessions.get(sendId);
		try {
			logger.info("第一 层：sendmsg :" + message.toString());
			sendSession.getBasicRemote().sendText(message.toString());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Override
	public void customerSave(Map<String, Object> map) {
		/**
		 * 暂时没有用到
		 */

	}

	/**
	 * session绑定方法
	 */
	@Override
	public void sessionBinding(Integer bingdingId) {
		logger.info("第一 层：存放session:" + bingdingId + session);
		this.id = bingdingId;
		sessions.put(bingdingId, session);
	}

	/**
	 * 解除session绑定
	 */
	@Override
	public void sessionUnBinding(Integer bingdingId) {
		System.out.println("第一 层：除去session:" + bingdingId + session);
		sessions.remove(bingdingId);
	}

	// ***********************************************************************************
	// =====================================返回经过第三层的转发消息===========================

	@Override
	public void send(Msg msg, Integer statusid) {
		System.out.println("第一 层：sendMsg");
		System.out.println("第一 层：statusid :" + statusid);
		JSONObject sendmsg = msgDavanning(msg);
		Session sendSession = sessions.get(statusid);
		try {
			System.out.println("第一 层：sendmsg :" + sendmsg.toString());
			if (sendSession != null) {
				sendSession.getBasicRemote().sendText(sendmsg.toString());
			} else {
				sessions.remove(statusid);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	// ***********************************************************************************
	// =====================================返回经过第三层的转发消息===========================

}
