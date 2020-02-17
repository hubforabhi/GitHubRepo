package com.abhi.spring.security.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
	//Need public getter and setter , else No converter found for return value of type error thrown
	private String id;
	private String fName;
	private String lName;
	
	public User(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
}
