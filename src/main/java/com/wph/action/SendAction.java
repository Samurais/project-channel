package com.wph.action;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("sendAction")
@Scope("prototype")
public class SendAction implements ServletRequestAware, SessionAware {
	// ********************************************************************************
	// ================================json对象=========================================
	private Map<String, Object> jsonMap;

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	
	// ********************************************************************************
	// ================================会话对象==========================================
	private Map<String, Object> session;
	private HttpServletRequest request;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}
	
	// ********************************************************************************
	// ================================action对象=======================================
	public String send() {
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			session.put(paraName, request.getParameter(paraName));
		}
		return "send";
	}

	public String get() {
		jsonMap = session;
		for(Map.Entry<String, Object> entry :session.entrySet()){
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		return "jsonMap";
	}



}
