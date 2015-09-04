package com.lambda.practice;

public class ReverseTheInteger {
	
	static void reverseTheInteger(int value) {
		int reverseValue=0;
		boolean negativeValue = false;
		if(value<0) { negativeValue = true; value = -value;}
		while(value>9) {
			reverseValue = reverseValue*10+ value%10;
			value = value/10;
		}
		reverseValue = reverseValue*10+ value;
		if(negativeValue)  reverseValue = -reverseValue;
		System.out.println(reverseValue);
	}
	
	static void reverseTheIntegerByRecursion(int value) {
		int reverseValue=0;
		boolean negativeValue = false;
		if(value<0) { negativeValue = true; value = -value;}
		reverseValue = reverse(value);
		if(negativeValue)  reverseValue = -reverseValue;
		System.out.println(reverseValue);

	}
	static int i=10;
	static int reverse(int value) {
		if(value<10) {
			return value;
		}		
		return value%10*(int) Math.pow(10, String.valueOf(value).length()-1) + reverse(value/10);
	}
	
	public static void main(String []args) {
		/*reverseTheInteger(12345);
		reverseTheInteger(10);
		reverseTheInteger(-0);
		reverseTheInteger(0);
		reverseTheInteger(-12345);
		*/
		reverseTheIntegerByRecursion(12345);
		reverseTheIntegerByRecursion(13579);
		reverseTheIntegerByRecursion(10);
	}

}
