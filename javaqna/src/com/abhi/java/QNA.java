package com.abhi.java;

public class QNA {
	public static void f1(String str) {
		System.out.println("String "+str);
	}
	
	public static void f1(Object ob) {
		System.out.println("Object "+ob);
	}

	public static void main(String []args) {
		f1(null);
		f2(123);
	}
	
	public static void f2(int i) {
		System.out.println("int "+i);
	}
	
	public static void f2(long i) {
		System.out.println("long "+i);
	}
	
	public static void f2(Integer i) {
		System.out.println("Integer "+i);
	}
}
