package com.abhi.java;

public interface Shape {
	public abstract int getArea();
	
	public default String getDescription() {
		return "This is shape and this is abstract";
	}
	
	public static String getMyDescription() {
		return "This is Abstract Shape";
	}
}