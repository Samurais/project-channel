package com.wph.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Customerrfmsample;
import com.wph.service.impl.BaseServiceImpl;

@Controller("customerrfmsampleAction")
@Scope("prototype")
public class CustomerrfmsampleAction extends BaseServiceImpl<Customerrfmsample> {
	// ********************************************************************************
	// ==================================�ͷ���д����������֤===============================
	public String insertSample(){
		
		return null;
	}
}
