package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Customer;
import com.wph.entities.Customerservice;

public interface CustomerService extends BaseService<Customer> {
	// 查询是否有客服位置,用来消息返回时的验证
	// id唯一验证
	public boolean idValid(Integer id);
	
	// 查询所有数据
	public Long getCount();
	
	// 分页查询
	public List<Customer> pageQuery(Integer limit, Integer offset);
	
	// 按照id组删除
	public void deleteByIds(String ids);
	
	// 模糊查询
	public List<Customer> pageQuery(Integer limit, Integer offset, String search);
	
	//登陆验证
	public String loginValidate(Integer id, String password);

	//带公司的登陆验证
	public String loginValidate(Integer loginid, String loginpassword, Integer companyid);
	
	//带公司验证的保存方法
	public void save(Customer model, Integer cpId);

	//带公司验证的页面请求
	public List<Customer> pageQuery(Integer limit, Integer offset, String search, Integer cpId);

	//带公司验证的数量统计
	public BigInteger getCount(Integer cpId);

	//带公司验证的修改
	public void update(Customer model, Integer companyid);
	
	//获得客户的公司ID
	public Integer getCompanyid(Integer customerid);
}
