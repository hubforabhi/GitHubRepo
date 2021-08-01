package com.nagarro.interview.questions;

public class Example2 {
	
	public static void main(String[] args) {
		//this will always call parent version
		Parent1 p1 = new Child1();
		p1.method1(123);
		p1.method1(Integer.valueOf(129));
		
		//just reverse, and this will always call child
	}
}
