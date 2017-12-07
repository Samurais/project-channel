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
	// =====================================��־����========================================
	private static Logger logger = Logger.getLogger(WebConnectImpl.class);
	// ***********************************************************************************
	// =====================================���ж���========================================
	private Session session;
	private HttpSession httpSession;
	private Integer id;
	private WebConnectService webConnectService;
	private static Map<Integer, Session> sessions = new HashMap<Integer, Session>();
	// ***********************************************************************************
	// ==============================Service�������========================================

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws IOException, InterruptedException {
		logger.info("websocket�յ����� :" + session);
		this.session = session;
		this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		if (httpSession != null) {
			// ApplicationContext ctx = WebApplicationContextUtils
			// .getRequiredWebApplicationContext(httpSession.getServletContext());
			try {
				ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");
				this.webConnectService = (WebConnectService) ctx.getBean("webConnectService");
			} catch (Exception e) {
				logger.error("��һ��:���beanʧ��");
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
		logger.info("websocket�ر�����");
	}

	@OnMessage
	public void onMessage(String message, EndpointConfig config) throws IOException, InterruptedException {
		logger.info("===========================�յ���Ϣ=================================");
		logger.info(message);
		webConnectService.getMsg(message, this);
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
	}

	// ***********************************************************************************
	// =====================================��д�ķ���======================================
	/**
	 * �����ϲ㴫��������Ϣ 1.����MsgΪString����
	 */
	@Override
	public JSONObject msgDavanning(Msg responsemsg) {
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		// ��Ϣ���ĸ��ֶ�:1.�ͻ�id 2.�ͷ�id 3.��Ϣ���� 4.����ʱ��5.����
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
	 * �����ڷ���㼴�ڶ�����˵���Ϣ 2.�������õ���Ϣ���� 1).������Ϣ���� 2).��ȡҪ���͵�id 3).����id���sessions����
	 * 4).��Ϣ����
	 */
	@Override
	public void responseMsg(Msg responsemsg) {
		Integer msgtype = (responsemsg.getMsgtype().getId()) % 1000;
		Integer sendId = null;
		Session sendSession = null;
		if ((msgtype >= 100) && (msgtype < 200)) {
			// ���ظ��ͷ�
			sendId = responsemsg.getCustomerservice().getCsId();
		} else if ((msgtype >= 200) && (msgtype < 300)) {
			// ���ظ��ͻ�
			sendId = responsemsg.getCustomer().getCtId();
		} else {
			logger.error("��һ��:msgtype Error");
			return;
		}
		JSONObject message = msgDavanning(responsemsg);
		sendSession = sessions.get(sendId);
		try {
			logger.info("��һ �㣺sendmsg :" + message.toString());
			sendSession.getBasicRemote().sendText(message.toString());
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

	@Override
	public void customerSave(Map<String, Object> map) {
		/**
		 * ��ʱû���õ�
		 */

	}

	/**
	 * session�󶨷���
	 */
	@Override
	public void sessionBinding(Integer bingdingId) {
		logger.info("��һ �㣺���session:" + bingdingId + session);
		this.id = bingdingId;
		sessions.put(bingdingId, session);
	}

	/**
	 * ���session��
	 */
	@Override
	public void sessionUnBinding(Integer bingdingId) {
		System.out.println("��һ �㣺��ȥsession:" + bingdingId + session);
		sessions.remove(bingdingId);
	}

	// ***********************************************************************************
	// =====================================���ؾ����������ת����Ϣ===========================

	@Override
	public void send(Msg msg, Integer statusid) {
		System.out.println("��һ �㣺sendMsg");
		System.out.println("��һ �㣺statusid :" + statusid);
		JSONObject sendmsg = msgDavanning(msg);
		Session sendSession = sessions.get(statusid);
		try {
			System.out.println("��һ �㣺sendmsg :" + sendmsg.toString());
			if (sendSession != null) {
				sendSession.getBasicRemote().sendText(sendmsg.toString());
			} else {
				sessions.remove(statusid);
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	// ***********************************************************************************
	// =====================================���ؾ����������ת����Ϣ===========================

}
