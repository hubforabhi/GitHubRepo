package com.nagarro.interview.questions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Util {

	public static void demo1() {
		Pattern pattern = Pattern.compile("[0-9]+");
		List<String> mainList = Arrays.asList("FEB","HAS","29","DAYS");
		List<String> newList = null;
		if(!LocalDate.now().isLeapYear()) {
			newList = new LinkedList<>();
			for(String str : mainList) {
				if(pattern.matcher(str).matches()) {
					newList.add("28");
				} else {
					newList.add(str);
				}
			}
			newList.stream().forEach(System.out::println);
		}
	}
	
	public static void main(String []args) {
		demo1();
	}
}
