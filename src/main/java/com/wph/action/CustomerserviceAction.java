package com.wph.action;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.wph.entities.Customerservice;
import com.wph.entities.Customerserviceloginrecord;
import com.wph.entities.Msg;

@Controller("customerserviceAction")
@Scope("prototype")
public class CustomerserviceAction extends BaseAction<Customerservice> {
	/**
	 * 父类实现了ModelDriven<T>,RequestAware,SessionAware,ApplicationAware 可以使用域对象
	 * model对象 可以使用customerserviceService对象 json的jsonList和jsonMap对象
	 */
	// ********************************************************************************
	// =================================辨明公司号码=====================================
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
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

	public String loginValidate(){
		//有三种返回状态 1.success 2.false 3.noexist
		//保存验证数据
		String result = customerserviceService.loginValidate(loginid, loginpassword);
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
		inputStream = new ByteArrayInputStream(result.getBytes());
		if(result.equals("success")){
			customerserviceloginrecordService.saveCustomerserviceloginrecord(request, loginid);
		}
		System.out.println("loginValidate");
		return "stream";
	}
	// ********************************************************************************
	// ==================================账号唯一查询=====================================
	public String idValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", customerserviceService.idValid(model.getCsId()));
		return "jsonMap";
	}
	// ********************************************************************************
	// ==================================分页查询========================================
	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", customerserviceService.getCount(companyid));
		jsonMap.put("rows", customerserviceService.pageQuery(limit, offset, search,companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================分页查询========================================
	public String pageQueryJoinRole() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", customerserviceService.getCount(companyid));
		jsonMap.put("rows", customerserviceService.pageQueryJoinRole(limit, offset, search,companyid));
		return "jsonMapJoinRole";
	}
	
	// ********************************************************************************
	// ==========================修改对应的onEditableSave方法=============================
	public String editUpdate() {
		customerserviceService.update(model,companyid);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}
	
	//编辑客服时间
	public String editService(){
		customerserviceService.editService(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}
	// ********************************************************************************
	// =================================save方法========================================

	public String save() {
		model.setCsRegisttime((new Timestamp(System.currentTimeMillis())));;
		System.out.println(model.getCsId());
		customerserviceService.save(model,companyid);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// =================================delete方法======================================
	public String deleteByIds() {
		System.out.println(ids);
		customerserviceService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	// *********************************************************************************
	// ===========================客服websocket连接方法====================================
	public String serviceConnectWebSocket(){
		waitserviceonlineService.serviceConnectWebSocket(model);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}
	// *********************************************************************************
	// ========================客服断开websocket连接方法====================================
	public String serviceCutWebSocket(){
		List<Integer> customerlist = null;
		Integer serviceid = model.getCsId();
		if (serviceid == null) {
			inputStream = new ByteArrayInputStream("false".getBytes());
			return "stream";
		}
		customerlist = chatonlineService.servicelogoutGetCustomer(serviceid);
		//webConnect.sessionUnBinding(serviceid);
		
		
		if(customerlist!=null){
			if(customerlist.size()>0){
				for(Integer customerid : customerlist){
					Msg msg = new Msg();
					msg.setCustomer(customerService.get(customerid));
					msg.setCustomerservice(customerserviceService.get(serviceid));
					msg.setMsgtype(msgtypeService.get(3209));
					msgService.save(msg);
					//webConnect.send(msg, customerid);
					
					infHandle.msgSubmit(msg);
				}
			}
		}
		waitserviceonlineService.servicelogout(serviceid);
		customerserviceonlineService.servicelogout(serviceid);
		chatonlineService.servicelogoutDeleteCustomer(serviceid);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}
	// *********************************************************************************
	// ====================================客服登出方法====================================
	public String servicelogout(){
		List<Integer> customerlist = null;
		Integer serviceid = model.getCsId();
		if (serviceid == null) {
			inputStream = new ByteArrayInputStream("false".getBytes());
			return "stream";
		}
		customerlist = chatonlineService.servicelogoutGetCustomer(serviceid);
		
		//webConnect.sessionUnBinding(serviceid);
		if(customerlist!=null){
			if(customerlist.size()>0){
				for(Integer customerid : customerlist){
					Msg msg = new Msg();
					msg.setCustomer(customerService.get(customerid));
					msg.setCustomerservice(customerserviceService.get(serviceid));
					msg.setMsgtype(msgtypeService.get(3209));
					msgService.save(msg);
					//webConnect.send(msg, customerid);
					infHandle.msgSubmit(msg);
					
				}
			}
		}
		waitserviceonlineService.servicelogout(serviceid);
		customerserviceonlineService.servicelogout(serviceid);
		chatonlineService.servicelogoutDeleteCustomer(serviceid);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}
	
}
