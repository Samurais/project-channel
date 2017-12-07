package com.wph.service;

import java.util.ArrayList;
import java.util.List;

import com.wph.entities.Customerservice;

public interface BaseService<T> {
	//1.保存
	public void save(T t);
	//2.修改
	public void update(T t);
	//3.查找
	public T get(Integer id);
	//4.查找所有
	public List<T> query();
	//5.删除
	public void delete(T t);
}
