package com.abhi.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamShowcase {
	
	public static void main(String ...args) {
		Stream<String> lang = Stream.of("A","B","C","D");
		lang.filter(x -> !x.equalsIgnoreCase("B")).collect(Collectors.toList()).forEach(System.out::println);
	}

}
