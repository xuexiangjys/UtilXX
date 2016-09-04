package com.example.testutil.data.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="student")
public class Student {

	@DatabaseField(generatedId=true)
	private long id;
	@DatabaseField(columnName="username")
	private String username;
	@DatabaseField(columnName="age")
	private int age;
	@DatabaseField(columnName="sex")
	private String sex;
//	@DatabaseField(columnName="score")
//	private int score;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
//	public int getScore() {
//		return score;
//	}
//	public void setScore(int score) {
//		this.score = score;
//	}
	
	public String toString() {
		return "id:" + id + ", username:" + username + ", age:" + age + ", sex:" + sex;
	}

}
