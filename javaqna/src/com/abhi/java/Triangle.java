package com.abhi.java;

import java.util.Optional;

public class Triangle implements Shape {
	@Override
	public int getArea() {
		return 0;
	}
	
	@Override
	public String getDescription() {
		return "This is Trianlge and This is concrete";
	}
	
	public static void main(String []args) {
		Integer int1 = null;
		Integer int2 = 10;
		Optional<Integer> intOptional = Optional.ofNullable(int1);
		System.out.println(intOptional.isPresent());
		System.out.println(intOptional.orElse(0));
		
		Shape shape = new Triangle();
		System.out.println(shape.getDescription());
		System.out.println(Shape.getMyDescription());
	}
}
