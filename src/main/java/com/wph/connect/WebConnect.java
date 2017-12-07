package com.wph.connect;

import java.util.Map;

import com.wph.entities.Msg;

import net.sf.json.JSONObject;

/**
 * 
 * @author wu �������������� ���Ӳ� ������Ϣ��ȡ����,���Ͻ�����Ϣ����
 */
public interface WebConnect {
	// 1.��Ϣ�ĳ�������
	public void responseMsg(Msg responsemsg);

	// 3.�ͻ���Ϣ�ĳ�����װ
	public void customerSave(Map<String, Object> map);

	// 5.��Ϣ�Ľ���
	public JSONObject msgDavanning(Msg responsemsg);
	
	//session��
	public void sessionBinding(Integer bingdingId);
	
	//session���
	public void sessionUnBinding(Integer bingdingId);
	
	//��Ϣ����
	public void send(Msg msg,Integer statusid);
}
