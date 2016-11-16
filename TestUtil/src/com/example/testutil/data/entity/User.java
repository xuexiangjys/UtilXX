package com.example.testutil.data.entity;

import com.hrw.framework.ahibernate.annotation.Column;
import com.hrw.framework.ahibernate.annotation.Id;
import com.hrw.framework.ahibernate.annotation.Table;

/**  
 * 创建时间：2016-2-4 下午3:17:07  
 * 项目名称：OrmDemo  
 * @author xuexiang
 * 文件名称：User.java  
 **/
@Table(name = "userinfo")
public class User {
	 @Id
	 private Long id;
	 
	 @Column(name = "loginname")
     private String loginname;
	 
	 @Column(name = "password")
	 private String password;
	    
     public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
