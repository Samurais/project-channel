package com.wph.information.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;

public class WechatProcess {
	/**
	 * 解析处理xml、获取智能回复结果（通过图灵机器人api接口）
	 * 
	 * @paramxml 接收到的微信数据
	 * @return 最终的解析结果（xml格式数据）
	 */

	public String processWechatMag(String xml) {
		/** 解析xml数据 */
		ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);
		System.out.println("收到微信消息:" + xml);

		/**
		 * 消息初步过滤
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
		 * 消息传递给封装层 获得封装层的对象 提交给封装层
		 */
		// ServletPacking servletPacking = (ServletPacking)
		// SpringUtils.getBean("servletPacking");
		// servletPacking.submitMsg(xmlEntity);

		/**
		 * 此时，如果用户输入的是“你好”，在经过上面的过程之后，result为“你也好”类似的内容
		 * 因为最终回复给微信的也是xml格式的数据，所有需要将其封装为文本类型返回消息
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

		//菜单点击事件
		if (xmlEntity.getEvent() != null && xmlEntity.getEvent() != "") {
			result = new ReceiveXmlFilter().clickFilter(xmlEntity,request);
			return null;
		}
		
		return null;
	}

}
