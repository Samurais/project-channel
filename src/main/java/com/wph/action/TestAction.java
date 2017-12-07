package com.wph.action;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.wph.model.FileImage;
import com.wph.service.MsgService;
import com.wph.util.impl.JsonUtils;

import net.sf.json.JSONObject;

@Controller("testAction")
@Scope("prototype")
public class TestAction extends BaseAction<FileImage> implements RequestAware, SessionAware, ApplicationAware{
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void test() throws IOException {
		System.out.println("触发请求");
		StringBuffer str = new StringBuffer();
		URL url = new URL("http://www.tuling123.com/openapi/api");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		//connection.setRequestProperty("charset", "UTF-8");
		connection.connect();

		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		JSONObject obj = new JSONObject();
		obj.element("key", "eccb5d806b254c888485d89cc08b13c9");
		System.out.println(date);
		obj.element("info", date);
		obj.element("userid", "123456");
		System.out.println(obj.toString());
		out.writeBytes(obj.toString());
		out.flush();
		out.close();

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		String lines;
		StringBuffer sb = new StringBuffer("");
		while ((lines = reader.readLine()) != null) {
			lines = new String(lines.getBytes(), "utf-8");
			sb.append(lines);
		}
		System.out.println(sb);
		reader.close();
		connection.disconnect();
	}
	
	
	private Map json;

	public Map getJson() {
		return json;
	}

	public void setJson(Map json) {
		this.json = json;
	}

	public String test2() throws IOException {
		String APIKEY = "eccb5d806b254c888485d89cc08b13c9";
		String question = date;// 这是上传给云机器人的问题
		String INFO = URLEncoder.encode(question, "utf-8");
		String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO+"&userid=123456";
		URL getUrl = new URL(getURL);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.connect();

		// 取得输入流，并使用Reader读取
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		// 断开连接
		connection.disconnect();
		System.out.println(sb);
		json = JsonUtils.jsonToMap(sb.toString());
		return "json";
	}
	
	
	private String limit;
	private String offset;
	private String departmentname;
	private String statu;
	
	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public void test3()
	{
		System.out.println("limit=" + limit);
		System.out.println("offset=" + offset);
		System.out.println("departmentname=" + departmentname);
		System.out.println("statu=" + statu);
		System.out.println("收到请求");
    }
	
	Map<String, Object> application;
	Map<String, Object> session;
	Map<String, Object> request;

	@Override
	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;
		
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
		
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
		
	}
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void test4(){
		
		System.out.println(message);
	}
	
	private String href;
	

	public void setHref(String href) {
		this.href = href;
	}

	public void testIp(){
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);  
		System.out.println(org.apache.struts2.ServletActionContext.getRequest().getRemoteAddr());
		System.out.println(href);
	}
	
	@Resource
	private MsgService msgService;
	
	public void setMsgService(MsgService msgService) {
		this.msgService = msgService;
	}
	
	private Map<String,Object> jsonMap;
	
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}


	public String test5(){
		jsonMap = new HashMap<String,Object>();
		jsonMap.put("rows", msgService.pageQuery(10, 0, null, 1122));
		return "jsonMap";
	}
	

}
