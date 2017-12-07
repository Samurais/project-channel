package com.wph.connect.service;

import com.wph.connect.WebConnect;
import com.wph.entities.Msg;
import com.wph.entities.Terminal;

/**
 * 
 * @author wu �������������� ����� ������Ϣ���ϳ�ͳһ��ʽ,�����ύ,ͬʱ���г����Ĺ��˺Ϳͻ���Ϣ��װ
 *
 *         ��Ϣ�ĸ�ʽ,Ϊ���ݿ�ʵ���� class Msg private Integer id; private Customerservice
 *         customerservice; private Customer customer; private Msgtype msgtype;
 *         private Timestamp sendtime; private String content; private String
 *         sensitiveword;
 */
public interface WebConnectService {

	public void getMsg(String message, WebConnect webConnect);

	public Msg msgPacking(String message);

	public void saveMsg(Msg msg);

	public void msgFilter(Msg msg);
	
	public void sendMsg(Msg msg,Terminal terminal);
	
}
