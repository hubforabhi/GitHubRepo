package com.lambda.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;

public class LambdaPractice {
	
	public static void main(String []args) {
		CountOfString cstr = param -> param.length();
		System.out.println(cstr.countOfString("Hello World from lamda Expression"));
		
		Function<Integer,String> intToStr = String :: valueOf;
		System.out.println(intToStr.apply(10));
		
		List<String> strList = Arrays.asList(new String[]{"str1","str2","str3","str4"});
		strList.forEach(str -> {
			printNames(() -> str);
		});
		
		Consumer<String> consumer = LambdaPractice::printNames;
		consumer.accept("Hello World From Consumer");
		
		IntBinaryOperator ibo = (x,y)-> x+y;
		System.out.println(ibo.applyAsInt(2, 3));
		
	}
	
	static void printNames(Supplier<String> supplier) {
		System.out.println(supplier.get());
	}
	
	static void printNames(String name) {
		System.out.println(name);
	}

}
