package com.wph.connect;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestListener implements ServletRequestListener {

	public void requestInitialized(ServletRequestEvent sre) {
		// ������request����Я����httpSession
		((HttpServletRequest) sre.getServletRequest()).getSession();

	}

	public RequestListener() {
		// TODO Auto-generated constructor stub
	}

	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
	}

}
