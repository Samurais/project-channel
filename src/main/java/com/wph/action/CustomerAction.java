package com.wph.action;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.wph.entities.Customer;
import com.wph.entities.Customerservice;
import com.wph.entities.Msg;

@Controller("customerAction")
@Scope("prototype")
public class CustomerAction extends BaseAction<Customer> {
	// ********************************************************************************
	// =================================辨明公司号码=====================================
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	// ********************************************************************************
	// ==================================账号注册========================================
	public String register() {
		customerService.save(model, companyid);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==================================账号登陆验证=====================================
	private Integer loginid;
	private String loginpassword;

	public void setLoginid(Integer loginid) {
		this.loginid = loginid;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}

	// 这个url为web端和移动端请求的url,微信端和其他终端请求其他url
	// 根据请求的url判断客户来源
	public String loginValidate() {
		// 登陆验证需要终端信息
		// 有三种返回状态 1.success 2.false 3.noexist
		// 保存验证数据
		String result = customerService.loginValidate(loginid, loginpassword, companyid);
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
		if (result == "success") {
			// 客户的登陆验证,客户登陆信息挖取
			customerloginrecordService.saveCustomerloginrecord(request, loginid);
		} else {
			customerloginrecordService.saveCustomerloginrecord(request, null);
		}
		inputStream = new ByteArrayInputStream(result.getBytes());
		return "stream";
	}
	
	
	// ********************************************************************************
	// ==================================账号唯一查询=====================================
	public String idValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", customerService.idValid(model.getCtId()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================分页查询========================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", customerService.getCount(companyid));
		jsonMap.put("rows", customerService.pageQuery(limit, offset, search, companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==========================修改对应的onEditableSave方法=============================
	public String editUpdate() {
		customerService.update(model, companyid);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================save方法========================================
	public String save() {
		System.out.println(model.getCtId());
		customerService.save(model, companyid);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================delete方法======================================
	public String deleteByIds() {
		System.out.println(ids);
		customerService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================客户登出方法======================================
	public void customerlogout() {
		List<Integer> customerserviceList = null;
		Integer customerid = model.getCtId();
		if (customerid == null) {
			return;
		}
		customerserviceList = chatonlineService.customerlogoutGetService(customerid);
		// session解除绑定
		//webConnect.sessionUnBinding(customerid);
		
		if (customerserviceList != null) {
			if (customerserviceList.size() > 0) {
				for (Integer customerserviceid : customerserviceList) {
					// 通知客服客户登出
					Msg msg = new Msg();
					msg.setCustomer(customerService.get(customerid));
					msg.setCustomerservice(customerserviceService.get(customerserviceid));
					msg.setMsgtype(msgtypeService.get(3109));
					msgService.save(msg);
					
					//webConnect.send(msg, customerserviceid);
					infHandle.msgSubmit(msg);

					Integer companyid = customerService.getCompanyid(customerid);
					// 客服窗口数目+1
					if (customerserviceService.isOnline(customerserviceid)) {
						// 客服等候服务表加上客服
						waitserviceonlineService.pushCustomerService(customerserviceService.get(customerserviceid));
						// 验证是否还有排队用户
						// 如果有,则通知客户进入
						if (!waitonlineService.isEmpty(companyid)) {
							System.out.println("waitonlineService.isEmpty=false");
							Integer csid = waitserviceonlineService.popCustomerService(companyid);
							Customerservice newcustomerservice = customerserviceService.get(csid);
							
							Integer ctid = waitonlineService.popCustomer(companyid);
							Customer newcustomer = customerService.get(ctid);
							
							Msg customermsg = new Msg();
							customermsg.setCustomer(newcustomer);
							customermsg.setCustomerservice(newcustomerservice);
							customermsg.setMsgtype(msgtypeService.get(3204));
							// 用websocket通知客户进入
							//webConnect.send(customermsg, newcustomer.getCtId());
							
							infHandle.msgSubmit(customermsg);
							Msg servicemsg = new Msg();
							servicemsg.setCustomer(newcustomer);
							servicemsg.setCustomerservice(newcustomerservice);
							servicemsg.setMsgtype(msgtypeService.get(3104));
							// 用webosocket通知客服进入
							
							//webConnect.send(servicemsg, newcustomerservice.getCsId());
							infHandle.msgSubmit(servicemsg);
							// 将会话状态加入会话状态表
							chatonlineService.pushChatonline(servicemsg);
							// 将会话开始信息写入会话记录表
							conversationService.beginConversation(servicemsg);
						}
						// 判断客户是否在线
					}

				}
			}
		}
		waitonlineService.customerlogout(customerid);
		customeronlineService.customerlogout(customerid);
		chatonlineService.customerlogoutDeleteService(customerid);
		return;
	}
}
