package org.litepal.litepalsample.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

public class User extends DataSupport {

	private long Id;

	@Column(nullable = false, defaultValue = "xuexiang")
	private String LoginName;

	@Column(defaultValue = "1234567")
	private String LoginPassword;
	
	@Column(defaultValue = "13913832432")
	private String PhoneNumber;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public String getLoginPassword() {
		return LoginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		LoginPassword = loginPassword;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	

}
