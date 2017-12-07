package com.wph.service;

import java.util.ArrayList;
import java.util.List;

import com.wph.entities.Customerservice;

public interface BaseService<T> {
	//1.����
	public void save(T t);
	//2.�޸�
	public void update(T t);
	//3.����
	public T get(Integer id);
	//4.��������
	public List<T> query();
	//5.ɾ��
	public void delete(T t);
}
