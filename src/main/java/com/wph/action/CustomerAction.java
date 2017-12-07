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
	// =================================������˾����=====================================
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	// ********************************************************************************
	// ==================================�˺�ע��========================================
	public String register() {
		customerService.save(model, companyid);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==================================�˺ŵ�½��֤=====================================
	private Integer loginid;
	private String loginpassword;

	public void setLoginid(Integer loginid) {
		this.loginid = loginid;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}

	// ���urlΪweb�˺��ƶ��������url,΢�Ŷ˺������ն���������url
	// ���������url�жϿͻ���Դ
	public String loginValidate() {
		// ��½��֤��Ҫ�ն���Ϣ
		// �����ַ���״̬ 1.success 2.false 3.noexist
		// ������֤����
		String result = customerService.loginValidate(loginid, loginpassword, companyid);
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
		if (result == "success") {
			// �ͻ��ĵ�½��֤,�ͻ���½��Ϣ��ȡ
			customerloginrecordService.saveCustomerloginrecord(request, loginid);
		} else {
			customerloginrecordService.saveCustomerloginrecord(request, null);
		}
		inputStream = new ByteArrayInputStream(result.getBytes());
		return "stream";
	}
	
	
	// ********************************************************************************
	// ==================================�˺�Ψһ��ѯ=====================================
	public String idValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", customerService.idValid(model.getCtId()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================��ҳ��ѯ========================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", customerService.getCount(companyid));
		jsonMap.put("rows", customerService.pageQuery(limit, offset, search, companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==========================�޸Ķ�Ӧ��onEditableSave����=============================
	public String editUpdate() {
		customerService.update(model, companyid);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================save����========================================
	public String save() {
		System.out.println(model.getCtId());
		customerService.save(model, companyid);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================delete����======================================
	public String deleteByIds() {
		System.out.println(ids);
		customerService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================�ͻ��ǳ�����======================================
	public void customerlogout() {
		List<Integer> customerserviceList = null;
		Integer customerid = model.getCtId();
		if (customerid == null) {
			return;
		}
		customerserviceList = chatonlineService.customerlogoutGetService(customerid);
		// session�����
		//webConnect.sessionUnBinding(customerid);
		
		if (customerserviceList != null) {
			if (customerserviceList.size() > 0) {
				for (Integer customerserviceid : customerserviceList) {
					// ֪ͨ�ͷ��ͻ��ǳ�
					Msg msg = new Msg();
					msg.setCustomer(customerService.get(customerid));
					msg.setCustomerservice(customerserviceService.get(customerserviceid));
					msg.setMsgtype(msgtypeService.get(3109));
					msgService.save(msg);
					
					//webConnect.send(msg, customerserviceid);
					infHandle.msgSubmit(msg);

					Integer companyid = customerService.getCompanyid(customerid);
					// �ͷ�������Ŀ+1
					if (customerserviceService.isOnline(customerserviceid)) {
						// �ͷ��Ⱥ�������Ͽͷ�
						waitserviceonlineService.pushCustomerService(customerserviceService.get(customerserviceid));
						// ��֤�Ƿ����Ŷ��û�
						// �����,��֪ͨ�ͻ�����
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
							// ��websocket֪ͨ�ͻ�����
							//webConnect.send(customermsg, newcustomer.getCtId());
							
							infHandle.msgSubmit(customermsg);
							Msg servicemsg = new Msg();
							servicemsg.setCustomer(newcustomer);
							servicemsg.setCustomerservice(newcustomerservice);
							servicemsg.setMsgtype(msgtypeService.get(3104));
							// ��webosocket֪ͨ�ͷ�����
							
							//webConnect.send(servicemsg, newcustomerservice.getCsId());
							infHandle.msgSubmit(servicemsg);
							// ���Ự״̬����Ự״̬��
							chatonlineService.pushChatonline(servicemsg);
							// ���Ự��ʼ��Ϣд��Ự��¼��
							conversationService.beginConversation(servicemsg);
						}
						// �жϿͻ��Ƿ�����
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
