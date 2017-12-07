package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Customerservice;

public interface CustomerserviceService extends BaseService<Customerservice>{
	//id唯一验证
	public boolean idValid(Integer id);
	
	//查询所有数据
	public Long getCount();
	
	//分页查询
	public List<Customerservice> pageQuery(Integer limit,Integer offset);
	
	//按照id组删除
	public void deleteByIds(String ids);
	
	//模糊查询
	public List<Customerservice> pageQuery(Integer limit,Integer offset,String search);
	
	//登陆验证
	public String loginValidate(Integer id,String password);
	
	//客服编辑信息
	public void editService(Customerservice service);
	

	//带公司验证的保存方法
	public void save(Customerservice model, Integer cpId);

	//带公司验证的页面请求
	public List<Customerservice> pageQuery(Integer limit, Integer offset, String search, Integer cpId);

	//带公司验证的数量统计
	public BigInteger getCount(Integer cpId);

	//带公司验证的修改
	public void update(Customerservice model, Integer companyid);
	
	//获得公司下的所有客服ID
	public List<Integer> getCustomerserviceIdList(Integer companyid);
	
	//获得公司下的所有客服ID:分页查询获得
	public List<Integer> getCustomerserviceIdList(Integer companyid,Integer limit, Integer offset);
	
	//将ids转换为String
	public String idListtoString(List<Integer> ids);
	
	//查看客服是否正在会话
	public Boolean isOnChat(Integer serviceid);
	
	//查看客服是否正在等待服务
	public Boolean isOnWait(Integer serviceid);
	
	//查看客服是否在线
	public Boolean isOnline(Integer serviceid);

	//左连接分页查询
	public List<Customerservice> pageQueryJoinRole(Integer limit, Integer offset, String search, Integer companyid);
	
}
