package com.wph.connect.service.solve;

import com.wph.connect.service.WebConnectService;
import com.wph.entities.Msg;
import com.wph.entities.Terminal;

public interface WebConnectServiceSolve {
	// 1.��Ϣ�Ľ���
	public Terminal msgAnalysis(Msg msg);

	// 2.��Ϣ�Ĵ���
	public void msgHandle(Msg msg, Terminal terminal);

	public void msgSubmit(Msg msg,WebConnectService webConnectService);

	public void msgSubmit(Msg msg);
}
