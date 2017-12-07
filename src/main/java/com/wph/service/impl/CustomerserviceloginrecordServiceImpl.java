package com.wph.service.impl;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.wph.entities.Customer;
import com.wph.entities.Customerloginrecord;
import com.wph.entities.Customerservice;
import com.wph.entities.Customerserviceloginrecord;
import com.wph.service.CustomerserviceloginrecordService;
import com.wph.util.impl.AddressUtils;
import com.wph.util.impl.GetKeyword;
import com.wph.util.impl.SystemUtils;

@Service("customerserviceloginrecordService")
public class CustomerserviceloginrecordServiceImpl extends BaseServiceImpl<Customerserviceloginrecord>
		implements CustomerserviceloginrecordService {

	@Override
	public void saveCustomerserviceloginrecord(HttpServletRequest request, Integer loginid) {
		Customerserviceloginrecord customerserviceloginrecord = new Customerserviceloginrecord();
		if (loginid != null) {
			Object customerservice = getSession().get(Customerservice.class, loginid);
			customerserviceloginrecord.setCustomerservice((Customerservice)customerservice);
		}
		// ��ȡIP,�������Ϣ,��������,ϵͳ�汾,mac��ַ
		String ipAddr = SystemUtils.getIpAddr(request);
		// ���mac��ַ̫����ʱ��
		// String macAddr = SystemUtils.getMacAddress(ipAddr);

		// ���IP��ַ�����ڵ���,��������ȡ��ʡ��
		AddressUtils addressUtil = new AddressUtils();
		String area = null;
		try {
			area = addressUtil.getAddress(ipAddr, "utf-8");
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		customerserviceloginrecord.setLoginarea(area);
		customerserviceloginrecord.setLoginip(ipAddr);
		customerserviceloginrecord.setLogintime(new Timestamp(System.currentTimeMillis()));
		getSession().save(customerserviceloginrecord);
		System.out.println("customerserviceloginrecord����ɹ�");

	}

}
