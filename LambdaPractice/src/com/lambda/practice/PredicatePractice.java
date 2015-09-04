package com.lambda.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicatePractice {
	
	public static Predicate<String> isNotBlankString() {
		return str -> str.length()!=0;
	}
	
	public static void main(String []args) {
		List<String> arr = Arrays.asList(new String[]{"arr1","arr2","arr3",""});
		System.out.println(arr.stream().filter(isNotBlankString()).collect(Collectors.<String>toList()));
	}

}
