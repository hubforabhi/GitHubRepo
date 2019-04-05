package com.abhi.java;

import java.util.Arrays;
import java.util.ResourceBundle;

public class Parent {

	public Parent() {
		System.out.println("default construtor");
	}
	
	public Parent(int a) {
		System.out.println("param construtor");
	}
	
	public void Parent() {
		System.out.println("method name as construtor");
	}

	public void Parent(int a) {
		System.out.println("method name as construtor with param");
	}

	public int add(int a, int b) {
		System.out.println("Parent");
		return a + b;
	}

	/*
	 * public void add(int a, int b, int c) { System.out.println("void"); }
	 * 
	 * public void add(int a, int b, int c) { System.out.println("Parent"); return
	 * a+b; }
	 */

	public static final void main(String arg) {
		System.out.println();
		String args[] = new String[] { "Hello", "World" };
		Arrays.asList(args).forEach(System.out::println);
	}

	public static void main(String[] args) {
		Parent parent = new Child();
		parent.add(1, 2);
		parent = new Parent(1);
		parent.Parent();parent.Parent(1);
		String mails = "";
		String[] splitIds = mails.split(",");
		Arrays.stream(splitIds).forEach(num -> System.out.println(num));

		ResourceBundle messageBundle = ResourceBundle.getBundle("messages");
		for (int i = 1; i < 10; i++) {
			if (messageBundle.containsKey("message_line_" + i))
				System.out.println(messageBundle.getString("message_line_" + i));
		}
		main("Hello World");
		// LocalDateTime.g

		Demo demo = new Demo() {
			public void show_implementation() {
				System.out.println("show implementation");
			}

			public void show2() {
				Demo.super.show2();
				System.out.println("show2 implementation");
			}
		};

		demo.show_implementation();
		Demo.show();
		demo.show2();
	}
}

@FunctionalInterface
interface Demo {
	public static void show() {
		System.out.println("Demo Interface static show");
	}

	public default void show2() {
		System.out.println("Default show");
	}
	
	private void show3() {
		
	}

	public abstract void show_implementation();
}
