package com.wph.information.handle;

import com.wph.entities.Msg;
import com.wph.entities.Terminal;

public interface InfHandleService {
	//消息分析,得到终端对象
	public Terminal msgAnalysis(Msg msg);
	
	//消息处理
	public void msgHandle(Msg msg, Terminal terminal);

}
