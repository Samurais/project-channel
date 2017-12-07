package com.wph.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.jboss.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wph.connect.WebConnectImpl;
import com.wph.information.handle.InfHandle;
import com.wph.service.AdministratorService;
import com.wph.service.CategoryService;
import com.wph.service.ChatonlineIdService;
import com.wph.service.ChatonlineService;
import com.wph.service.CompanyMonitorService;
import com.wph.service.CompanyService;
import com.wph.service.ConversationService;
import com.wph.service.CustomerInfoService;
import com.wph.service.CustomerService;
import com.wph.service.CustomerloginrecordService;
import com.wph.service.CustomeronlineService;
import com.wph.service.CustomerrfmService;
import com.wph.service.CustomerserviceInfoService;
import com.wph.service.CustomerserviceService;
import com.wph.service.CustomerserviceloginrecordService;
import com.wph.service.CustomerserviceonlineService;
import com.wph.service.CustomerservicerightService;
import com.wph.service.CustomerserviceroleService;
import com.wph.service.KnowledgebaseService;
import com.wph.service.MsgService;
import com.wph.service.MsgtypeService;
import com.wph.service.OrderService;
import com.wph.service.ProductService;
import com.wph.service.QuickmessageService;
import com.wph.service.RightService;
import com.wph.service.RobotknowledgebaseService;
import com.wph.service.RoleService;
import com.wph.service.ServiceActiveService;
import com.wph.service.ShortMsgService;
import com.wph.service.TerminalService;
import com.wph.service.TerminaltypeService;
import com.wph.service.UserroleService;
import com.wph.service.WaitonlineService;
import com.wph.service.WaitserviceonlineService;
import com.wph.util.FileUpload;
import com.wph.util.Jcseg;

@Controller("baseAction")
@Scope("prototype")
public class BaseAction<T> implements ModelDriven<T>, RequestAware, SessionAware, ApplicationAware {
	// ********************************************************************************
	// ===============================获得log对象========================================
	protected static Logger logger = Logger.getLogger(WebConnectImpl.class);
	// ********************************************************************************
	// ==========================获得分页和查询对象========================================
	protected Integer limit;
	protected Integer offset;
	protected String search;

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	// ********************************************************************************
	// ==========================获得批量删除id对象========================================
	protected String ids;

	public void setIds(String ids) {
		this.ids = ids;
	}


	// ********************************************************************************
	// ==========================获得域对象==============================================
	protected Map<String, Object> request;
	protected Map<String, Object> Session;
	protected Map<String, Object> Application;

	@Override
	public void setApplication(Map<String, Object> arg0) {
		this.Application = arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.Session = arg0;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	// ********************************************************************************
	// ==========================获得model的Service对象==================================
	@Resource
	protected CompanyMonitorService companyMonitorService;
	@Resource
	protected ServiceActiveService serviceActiveService;

	public void setCompanyMonitorService(CompanyMonitorService companyMonitorService) {
		this.companyMonitorService = companyMonitorService;
	}

	public void setServiceActiveService(ServiceActiveService serviceActiveService) {
		this.serviceActiveService = serviceActiveService;
	}

	// ********************************************************************************
	// ==========================获得model对象===========================================
	protected T model;
	private Class clazz;

	@Override
	public T getModel() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return model;
	}

	// ********************************************************************************
	// =========================获得websocket服务对象=====================================
	// @Resource
	// protected WebConnect webConnect;
	//
	// public void setWebConnect(WebConnect webConnect) {
	// this.webConnect = webConnect;
	// }
	@Resource
	protected InfHandle infHandle;

	public void setInfHandle(InfHandle infHandle) {
		this.infHandle = infHandle;
	}
	// ********************************************************************************
	// ==========================获得Service对象=========================================

	@Resource
	protected ChatonlineIdService chatonlineIdService;
	@Resource
	protected ChatonlineService chatonlineService;
	@Resource
	protected CompanyService companyService;
	@Resource
	protected ConversationService conversationService;
	@Resource
	protected CustomerloginrecordService customerloginrecordService;
	@Resource
	protected CustomeronlineService customeronlineService;
	@Resource
	protected CustomerService customerService;
	@Resource
	protected CustomerserviceloginrecordService customerserviceloginrecordService;
	@Resource
	protected CustomerserviceonlineService customerserviceonlineService;
	@Resource
	protected CustomerserviceService customerserviceService;
	@Resource
	protected MsgService msgService;
	@Resource
	protected MsgtypeService msgtypeService;
	@Resource
	protected TerminalService terminalService;
	@Resource
	protected TerminaltypeService TerminaltypeService;
	@Resource
	protected WaitonlineService waitonlineService;
	@Resource
	protected WaitserviceonlineService waitserviceonlineService;
	@Resource
	protected ShortMsgService shortMsgService;
	@Resource
	protected OrderService orderService;
	@Resource
	protected ProductService productService;
	@Resource
	protected CustomerInfoService customerInfoService;
	@Resource
	protected CustomerserviceInfoService customerserviceInfoService;
	@Resource
	protected CustomerrfmService customerrfmService;
	@Resource
	protected CategoryService categoryService;
	@Resource
	protected KnowledgebaseService knowledgebaseService;
	@Resource
	protected AdministratorService administratorService;
	@Resource
	protected RightService rightService;
	@Resource
	protected RoleService roleService;
	@Resource
	protected UserroleService userroleService;
	@Resource
	protected RobotknowledgebaseService robotknowledgebaseService;
	@Resource
	protected CustomerservicerightService customerservicerightService;
	@Resource
	protected CustomerserviceroleService customerserviceroleService;
	@Resource
	protected QuickmessageService quickmessageService;
	
	public void setChatonlineIdService(ChatonlineIdService chatonlineIdService) {
		this.chatonlineIdService = chatonlineIdService;
	}

	public void setChatonlineService(ChatonlineService chatonlineService) {
		this.chatonlineService = chatonlineService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public void setConversationService(ConversationService conversationService) {
		this.conversationService = conversationService;
	}

	public void setCustomerloginrecordService(CustomerloginrecordService customerloginrecordService) {
		this.customerloginrecordService = customerloginrecordService;
	}

	public void setCustomeronlineService(CustomeronlineService customeronlineService) {
		this.customeronlineService = customeronlineService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setCustomerserviceloginrecordService(
			CustomerserviceloginrecordService customerserviceloginrecordService) {
		this.customerserviceloginrecordService = customerserviceloginrecordService;
	}

	public void setCustomerserviceonlineService(CustomerserviceonlineService customerserviceonlineService) {
		this.customerserviceonlineService = customerserviceonlineService;
	}

	public void setCustomerserviceService(CustomerserviceService customerserviceService) {
		this.customerserviceService = customerserviceService;
	}

	public void setMsgService(MsgService msgService) {
		this.msgService = msgService;
	}

	public void setMsgtypeService(MsgtypeService msgtypeService) {
		this.msgtypeService = msgtypeService;
	}

	public void setTerminalService(TerminalService terminalService) {
		this.terminalService = terminalService;
	}

	public void setTerminaltypeService(TerminaltypeService terminaltypeService) {
		TerminaltypeService = terminaltypeService;
	}

	public void setWaitonlineService(WaitonlineService waitonlineService) {
		this.waitonlineService = waitonlineService;
	}

	public void setWaitserviceonlineService(WaitserviceonlineService waitserviceonlineService) {
		this.waitserviceonlineService = waitserviceonlineService;
	}

	public void setShortMsgService(ShortMsgService shortMsgService) {
		this.shortMsgService = shortMsgService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCustomerInfoService(CustomerInfoService customerInfoService) {
		this.customerInfoService = customerInfoService;
	}

	public void setCustomerserviceInfoService(CustomerserviceInfoService customerserviceInfoService) {
		this.customerserviceInfoService = customerserviceInfoService;
	}

	public void setCustomerrfmService(CustomerrfmService customerrfmService) {
		this.customerrfmService = customerrfmService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setKnowledgebaseService(KnowledgebaseService knowledgebaseService) {
		this.knowledgebaseService = knowledgebaseService;
	}

	public void setAdministratorService(AdministratorService administratorService) {
		this.administratorService = administratorService;
	}

	public void setRightService(RightService rightService) {
		this.rightService = rightService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setUserroleService(UserroleService userroleService) {
		this.userroleService = userroleService;
	}

	public void setRobotknowledgebaseService(RobotknowledgebaseService robotknowledgebaseService) {
		this.robotknowledgebaseService = robotknowledgebaseService;
	}

	public void setCustomerservicerightService(CustomerservicerightService customerservicerightService) {
		this.customerservicerightService = customerservicerightService;
	}

	public void setCustomerserviceroleService(CustomerserviceroleService customerserviceroleService) {
		this.customerserviceroleService = customerserviceroleService;
	}

	public void setQuickmessageService(QuickmessageService quickmessageService) {
		this.quickmessageService = quickmessageService;
	}

	// ********************************************************************************
	// =============================图片下载工具对象=======================================
	@Resource
	protected FileUpload fileUpload;
	@Resource
	protected Jcseg jcseg;

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

	public void setJcseg(Jcseg jcseg) {
		this.jcseg = jcseg;
	}

	// ********************************************************************************
	// ================================json对象=========================================
	protected List jsonList;

	public List getJsonList() {
		return jsonList;
	}

	protected Map<String, Object> jsonMap;

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	// ********************************************************************************
	// ================================stream对象=========================================
	protected InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

}
