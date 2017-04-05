package com.example.testutil.common.entity;

/**
 * 创建时间：2017-4-2 下午11:18:21 项目名称：UtilTest
 * 
 * @author xuexiang 文件名称：Person.java
 **/
public class Person {

	/**
	 * 姓名
	 */
	private String Name;
	/**
	 * 性别
	 */
	private String Gender;
	/**
	 * 年龄
	 */
	private String Age;
	/**
	 * 地址
	 */
	private String Address;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

}
