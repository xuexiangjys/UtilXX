package com.xuexiang.view.corepage.core;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class PageItem implements Serializable {
	private String name;
	// 页面名
	private String classPath;
	// 页面class
	private String params = "";

	public PageItem() {

	}
	
	public PageItem(String name, String classPath) {
		this.name = name;
		this.classPath = classPath;
	}
	
	public PageItem(String name, Class<?> clazz) {
		this.name = name;
		this.classPath = clazz.getName();
	}
	
	public PageItem(String name, String classPath, String params) {
		this.name = name;
		this.classPath = classPath;
		this.params = params;
	}
	
	public PageItem(String name, Class<?> clazz, Map<String, Object> params) {
		this.name = name;
		this.classPath = clazz.getName();
		this.params = JSON.toJSONString(params);
	}

	public String getName() {
		return name;
	}

	public PageItem setName(String name) {
		this.name = name;
		return this;
	}

	public String getClassPath() {
		return classPath;
	}

	public PageItem setClassPath(String classPath) {
		this.classPath = classPath;
		return this;
	}

	public PageItem setClassPath(Class<?> clazz) {
		classPath = clazz.getName();
		return this;
	}

	public String getParams() {
		return params;
	}

	public PageItem setParams(String params) {
		this.params = params;
		return this;
	}

	public PageItem setParams(Map<String, Object> params) {
		this.params = JSON.toJSONString(params);
		return this;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
