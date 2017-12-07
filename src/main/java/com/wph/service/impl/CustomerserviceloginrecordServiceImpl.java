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
		// 获取IP,浏览器信息,主机名字,系统版本,mac地址
		String ipAddr = SystemUtils.getIpAddr(request);
		// 获得mac地址太消耗时间
		// String macAddr = SystemUtils.getMacAddress(ipAddr);

		// 获得IP地址的所在地区,并单独抽取出省份
		AddressUtils addressUtil = new AddressUtils();
		String area = null;
		try {
			area = addressUtil.getAddress(ipAddr, "utf-8");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		customerserviceloginrecord.setLoginarea(area);
		customerserviceloginrecord.setLoginip(ipAddr);
		customerserviceloginrecord.setLogintime(new Timestamp(System.currentTimeMillis()));
		getSession().save(customerserviceloginrecord);
		System.out.println("customerserviceloginrecord保存成功");

	}

}
