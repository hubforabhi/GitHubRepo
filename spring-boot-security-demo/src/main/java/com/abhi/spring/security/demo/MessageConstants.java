package com.abhi.spring.security.demo;

public enum MessageConstants {
	LOGIN_FAILED("login failed"),
	LOGIN_CREDENTIALS_EXPIRED("login credentials expired");
	
	private String message;
	MessageConstants(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return this.message;
	}
}
