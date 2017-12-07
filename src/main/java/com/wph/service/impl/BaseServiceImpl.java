package com.wph.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.wph.entities.Msg;
import com.wph.service.BaseService;

@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {
	// ********************************************************************************
	// ==========================通过反射获得T的类型=======================================
	private Class clazz;

	public BaseServiceImpl() {
		ParameterizedType parenttype = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class) parenttype.getActualTypeArguments()[0];
	}

	// ********************************************************************************
	// ==========================获得sesionFactory======================================
	@Resource
	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// ********************************************************************************
	// ==========================基本的CRUD方法===========================================
	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public T get(Integer id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> query() {
		String hql = "from " + clazz.getSimpleName();
		return getSession().createQuery(hql).list();
	}

	@Override
	public void delete(T t) {
		getSession().delete(t);
	}
	
}
