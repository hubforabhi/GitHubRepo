package com.nagarro.interview.questions;

public class Parent {
	public void method1() throws ArithmeticException {
		// changing ArithmaticException to RuntimeException for Parent/Child don't have affect as both are unchecked
		System.out.println("Parent");
	}
}
