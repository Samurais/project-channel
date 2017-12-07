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
	// ==========================���Service�������======================================
	private CustomerService customerService = (CustomerService) SpringUtils.getBean("customerService");
	private CustomerloginrecordService customerloginrecordService = (CustomerloginrecordService) SpringUtils
			.getBean("customerloginrecordService");
	private TerminalService terminalService = (TerminalService) SpringUtils.getBean("terminalService");

	// ********************************************************************************
	// =================================����״̬����======================================
	/**
	 * ��¼�û���������Ϣ��ʲô ��һ��������openId,�ڶ�����������һ����Ϣ��״̬�� ����ͻ����ڻỰ,���¼�Ự״̬
	 */
	private static Map<String, String> customerstatus = new HashMap<String, String>();

	/**
	 * ��¼�ͻ���¼��Ϣ
	 */
	private static Map<String, Integer> companyid = new HashMap<String, Integer>();
	private static Map<String, Integer> loginid = new HashMap<String, Integer>();
	private static Map<String, String> password = new HashMap<String, String>();

	/**
	 * ��¼���ڻỰ�Ŀͷ��Ϳͻ�
	 */
	private static Map<Integer, Integer> chatonline = new HashMap<Integer, Integer>();

	
	
	// ********************************************************************************
	// =================================��ͨ��Ϣ�¼�======================================
	public String textFilter(ReceiveXmlEntity xmlEntity) {
		String result = "";
		String content = xmlEntity.getContent();
		String fromusername = xmlEntity.getFromUserName();
		String str = null;
		Boolean isfirst = customerstatus.get(fromusername) == null;
		System.out.println(customerstatus.size());
		/**
		 * ��¼��֤����
		 */
		if (isfirst) {
			/**
			 * ����ǵ�һ������ �鿴�Ƿ��������¼��֤
			 */
			if (content.indexOf("��¼") != -1) {
				str = "�������˺�:";
				result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
						str);
				customerstatus.put(fromusername, "�˺�");
				return result;
			}
			if (content.indexOf("�˹�") != -1) {

			}
		} else {
			/**
			 * ����ǵڶ�������, �鿴��һ�������Ƿ��������¼
			 */
			String laststatus = customerstatus.get(fromusername);
			if (laststatus.equals("�˺�")) {
				try {
					loginid.put(fromusername, Integer.parseInt(content));
				} catch (ClassCastException e) {
					str = "�˺��������!";
					result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(),
							xmlEntity.getToUserName(), str);
					customerstatus.remove(fromusername);
					return result;
				}
				str = "����������:";
				result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
						str);
				customerstatus.put(fromusername, "����");
				return result;
			}
			/**
			 * �鿴��һ�������Ƿ�����������
			 */
			if (laststatus.equals("����")) {
				password.put(fromusername, content);
				Integer id = loginid.get(fromusername);
				String ps = password.get(fromusername);
				Integer cpid = companyid.get(fromusername);

				try {
					String rs = customerService.loginValidate(id, ps, cpid);
					if (rs == "success") {

						str = "�󶨳ɹ�!";
						result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(),
								xmlEntity.getToUserName(), str);
						customerstatus.remove(fromusername);
						terminalService.saveTerminal(id, fromusername, 102, "�ͻ�");

					} else {
						str = "��ʧ��!";
						result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(),
								xmlEntity.getToUserName(), str);
						customerstatus.remove(fromusername);

					}
				} catch (Exception e) {

				}

				return result;
			}
			if (laststatus.equals("�Ự")) {
				return null;
			}
		}
		str = new TulingApiProcess().getTulingResult(xmlEntity.getContent());
		result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), str);
		return result;
	}

	// ********************************************************************************
	// =================================����¼�======================================
	public String clickFilter(ReceiveXmlEntity xmlEntity) {
		String eventKey = xmlEntity.getEventKey();
		String str = null;
		String result = null;
		String cpid = eventKey.substring(eventKey.indexOf("&") + 1, eventKey.length());

		Integer statusid = terminalService.checkBinding(xmlEntity.getFromUserName());
		if (eventKey.indexOf("login") != -1) {
			if (statusid != null) {
				str = "�����˺��Ѿ���ID:" + statusid;
				result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
						str);
				return result;
			}
			companyid.put(xmlEntity.getFromUserName(), Integer.parseInt(cpid));
			str = "�������˺�:";
			result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
					str);
			customerstatus.put(xmlEntity.getFromUserName(), "�˺�");
			return result;
		} else if (eventKey.indexOf("requestchat") != -1) {
			str = "����ǰ�滹��0���ˣ�����΢�ȴ����ͷ����Ͼ���~";
			result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
					str);
			customerstatus.put(xmlEntity.getFromUserName(), "�Ự");
			return result;
		}
		return null;
	}
	
	// ********************************************************************************
	// =================================����¼�======================================
	public String clickFilter(ReceiveXmlEntity xmlEntity,HttpRequest request) {
		String eventKey = xmlEntity.getEventKey();
		String str = null;
		String result = null;
		String cpid = eventKey.substring(eventKey.indexOf("&") + 1, eventKey.length());

		Integer statusid = terminalService.checkBinding(xmlEntity.getFromUserName());
		if (eventKey.indexOf("login") != -1) {
			if (statusid != null) {
				str = "�����˺��Ѿ���ID:" + statusid;
				result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
						str);
				return result;
			}
			companyid.put(xmlEntity.getFromUserName(), Integer.parseInt(cpid));
			str = "�������˺�:";
			result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
					str);
			customerstatus.put(xmlEntity.getFromUserName(), "�˺�");
			return result;
		} else if (eventKey.indexOf("requestchat") != -1) {
			str = "����ǰ�滹��0���ˣ�����΢�ȴ����ͷ����Ͼ���~";
			result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
					str);
			return result;
		}
		return null;
	}
}
