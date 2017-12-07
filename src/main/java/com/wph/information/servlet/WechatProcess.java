package com.wph.information.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;

public class WechatProcess {
	/**
	 * ��������xml����ȡ���ܻظ������ͨ��ͼ�������api�ӿڣ�
	 * 
	 * @paramxml ���յ���΢������
	 * @return ���յĽ��������xml��ʽ���ݣ�
	 */

	public String processWechatMag(String xml) {
		/** ����xml���� */
		ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);
		System.out.println("�յ�΢����Ϣ:" + xml);

		/**
		 * ��Ϣ��������
		 * 
		 */
		String result = "";
		String type = xmlEntity.getEvent();
		System.out.println(xmlEntity.getEvent());

//		if (xmlEntity.getEvent() != null && xmlEntity.getEvent() != "") {
//			result = new ReceiveXmlFilter().clickFilter(xmlEntity);
//		} else {
//			result = new ReceiveXmlFilter().textFilter(xmlEntity);
//		}
		
		if(xmlEntity.getEvent()==null || xmlEntity.getEvent()==""){
			result = new ReceiveXmlFilter().textFilter(xmlEntity);
		}else if(xmlEntity.getEvent().equals("CLICK")){
			result = new ReceiveXmlFilter().clickFilter(xmlEntity);
		}
		
		/**
		 * ��Ϣ���ݸ���װ�� ��÷�װ��Ķ��� �ύ����װ��
		 */
		// ServletPacking servletPacking = (ServletPacking)
		// SpringUtils.getBean("servletPacking");
		// servletPacking.submitMsg(xmlEntity);

		/**
		 * ��ʱ������û�������ǡ���á����ھ�������Ĺ���֮��resultΪ����Ҳ�á����Ƶ�����
		 * ��Ϊ���ջظ���΢�ŵ�Ҳ��xml��ʽ�����ݣ�������Ҫ�����װΪ�ı����ͷ�����Ϣ
		 **/
		// result = new
		// FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(),
		// xmlEntity.getToUserName(), result);

		return result;
	}

	private static Map<String, HttpSession> infomap = new HashMap<String, HttpSession>();

	private HttpRequest request;

	public String processWechatMag(ReceiveXmlEntity xmlEntity, HttpSession session, HttpRequest request) {
		String from = xmlEntity.getFromUserName();
		infomap.put(from, session);
		this.request = request;

		String result = "";
		String type = xmlEntity.getEvent();
		System.out.println(xmlEntity.getEvent());

		//�˵�����¼�
		if (xmlEntity.getEvent() != null && xmlEntity.getEvent() != "") {
			result = new ReceiveXmlFilter().clickFilter(xmlEntity,request);
			return null;
		}
		
		return null;
	}

}
