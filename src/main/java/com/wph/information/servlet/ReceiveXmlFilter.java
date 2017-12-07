package com.wph.information.servlet;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpRequest;

import com.wph.service.CustomerService;
import com.wph.service.CustomerloginrecordService;
import com.wph.service.TerminalService;
import com.wph.util.impl.SpringUtils;

public class ReceiveXmlFilter {
	// ********************************************************************************
	// ==========================获得Service服务对象======================================
	private CustomerService customerService = (CustomerService) SpringUtils.getBean("customerService");
	private CustomerloginrecordService customerloginrecordService = (CustomerloginrecordService) SpringUtils
			.getBean("customerloginrecordService");
	private TerminalService terminalService = (TerminalService) SpringUtils.getBean("terminalService");

	// ********************************************************************************
	// =================================保存状态对象======================================
	/**
	 * 记录用户的上条消息是什么 第一个参数是openId,第二个参数是上一条消息的状态码 如果客户正在会话,则记录会话状态
	 */
	private static Map<String, String> customerstatus = new HashMap<String, String>();

	/**
	 * 记录客户登录信息
	 */
	private static Map<String, Integer> companyid = new HashMap<String, Integer>();
	private static Map<String, Integer> loginid = new HashMap<String, Integer>();
	private static Map<String, String> password = new HashMap<String, String>();

	/**
	 * 记录正在会话的客服和客户
	 */
	private static Map<Integer, Integer> chatonline = new HashMap<Integer, Integer>();

	
	
	// ********************************************************************************
	// =================================普通消息事件======================================
	public String textFilter(ReceiveXmlEntity xmlEntity) {
		String result = "";
		String content = xmlEntity.getContent();
		String fromusername = xmlEntity.getFromUserName();
		String str = null;
		Boolean isfirst = customerstatus.get(fromusername) == null;
		System.out.println(customerstatus.size());
		/**
		 * 登录验证部分
		 */
		if (isfirst) {
			/**
			 * 如果是第一次请求 查看是否是请求登录验证
			 */
			if (content.indexOf("登录") != -1) {
				str = "请输入账号:";
				result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
						str);
				customerstatus.put(fromusername, "账号");
				return result;
			}
			if (content.indexOf("人工") != -1) {

			}
		} else {
			/**
			 * 如果是第二次请求, 查看上一次请求是否是请求登录
			 */
			String laststatus = customerstatus.get(fromusername);
			if (laststatus.equals("账号")) {
				try {
					loginid.put(fromusername, Integer.parseInt(content));
				} catch (ClassCastException e) {
					str = "账号输入错误!";
					result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(),
							xmlEntity.getToUserName(), str);
					customerstatus.remove(fromusername);
					return result;
				}
				str = "请输入密码:";
				result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
						str);
				customerstatus.put(fromusername, "密码");
				return result;
			}
			/**
			 * 查看上一次请求是否是请求密码
			 */
			if (laststatus.equals("密码")) {
				password.put(fromusername, content);
				Integer id = loginid.get(fromusername);
				String ps = password.get(fromusername);
				Integer cpid = companyid.get(fromusername);

				try {
					String rs = customerService.loginValidate(id, ps, cpid);
					if (rs == "success") {

						str = "绑定成功!";
						result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(),
								xmlEntity.getToUserName(), str);
						customerstatus.remove(fromusername);
						terminalService.saveTerminal(id, fromusername, 102, "客户");

					} else {
						str = "绑定失败!";
						result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(),
								xmlEntity.getToUserName(), str);
						customerstatus.remove(fromusername);

					}
				} catch (Exception e) {

				}

				return result;
			}
			if (laststatus.equals("会话")) {
				return null;
			}
		}
		str = new TulingApiProcess().getTulingResult(xmlEntity.getContent());
		result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), str);
		return result;
	}

	// ********************************************************************************
	// =================================点击事件======================================
	public String clickFilter(ReceiveXmlEntity xmlEntity) {
		String eventKey = xmlEntity.getEventKey();
		String str = null;
		String result = null;
		String cpid = eventKey.substring(eventKey.indexOf("&") + 1, eventKey.length());

		Integer statusid = terminalService.checkBinding(xmlEntity.getFromUserName());
		if (eventKey.indexOf("login") != -1) {
			if (statusid != null) {
				str = "您的账号已经绑定ID:" + statusid;
				result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
						str);
				return result;
			}
			companyid.put(xmlEntity.getFromUserName(), Integer.parseInt(cpid));
			str = "请输入账号:";
			result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
					str);
			customerstatus.put(xmlEntity.getFromUserName(), "账号");
			return result;
		} else if (eventKey.indexOf("requestchat") != -1) {
			str = "您的前面还有0个人，请稍微等待，客服马上就来~";
			result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
					str);
			customerstatus.put(xmlEntity.getFromUserName(), "会话");
			return result;
		}
		return null;
	}
	
	// ********************************************************************************
	// =================================点击事件======================================
	public String clickFilter(ReceiveXmlEntity xmlEntity,HttpRequest request) {
		String eventKey = xmlEntity.getEventKey();
		String str = null;
		String result = null;
		String cpid = eventKey.substring(eventKey.indexOf("&") + 1, eventKey.length());

		Integer statusid = terminalService.checkBinding(xmlEntity.getFromUserName());
		if (eventKey.indexOf("login") != -1) {
			if (statusid != null) {
				str = "您的账号已经绑定ID:" + statusid;
				result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
						str);
				return result;
			}
			companyid.put(xmlEntity.getFromUserName(), Integer.parseInt(cpid));
			str = "请输入账号:";
			result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
					str);
			customerstatus.put(xmlEntity.getFromUserName(), "账号");
			return result;
		} else if (eventKey.indexOf("requestchat") != -1) {
			str = "您的前面还有0个人，请稍微等待，客服马上就来~";
			result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
					str);
			return result;
		}
		return null;
	}
}
