
package com.example.testutil.data.entity;

import com.hrw.framework.ahibernate.annotation.Column;
import com.hrw.framework.ahibernate.annotation.Id;
import com.hrw.framework.ahibernate.annotation.Table;

@Table(name = "patient")
public class Patient {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "age")
    private String age;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient() {

    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
