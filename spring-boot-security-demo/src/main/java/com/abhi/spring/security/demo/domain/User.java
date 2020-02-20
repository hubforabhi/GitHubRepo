package com.abhi.spring.security.demo.domain;

import lombok.Data;

@Data
public class User {
	//Need public getter and setter , else No converter found for return value of type error thrown
	private String id;
	private String fName;
	private String lName;
	
}