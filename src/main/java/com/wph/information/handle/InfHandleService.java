package com.wph.information.handle;

import com.wph.entities.Msg;
import com.wph.entities.Terminal;

public interface InfHandleService {
	//��Ϣ����,�õ��ն˶���
	public Terminal msgAnalysis(Msg msg);
	
	//��Ϣ����
	public void msgHandle(Msg msg, Terminal terminal);

}
