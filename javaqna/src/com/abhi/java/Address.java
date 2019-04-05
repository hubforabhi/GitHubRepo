package com.abhi.java;

import java.io.Serializable;

public class Address implements Serializable { //if not marked as with Serializable , java.io.NotSerializableException is throw from Employee

	private String addressLine1;
	private String addressLine2;
	private String city;
	private int pinCode;
	
	public Address(String addressLine1, String addressLine2, String city, int pinCode) {
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.pinCode = pinCode;
		System.out.println("Address is from City "+ city+", Pin Code "+pinCode);
	}

	public String getAddressLine1() {
		return addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public String getCity() {
		return city;
	}
	public int getPinCode() {
		return pinCode;
	}
}
