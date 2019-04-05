package com.abhi.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class QNA {
	public static void f2(String str) {
		System.out.println("String "+str);
	}
	
	public static void f1(Object ob) {
		System.out.println("Object "+ob);
	}
	
	public static Optional<Child> getChild() {
		Child child = new Child();
		Optional<Child> childOpt = Optional.of(null);
		return childOpt;
	}

	public static void main(String []args) {		
		//f1(null);
		//f1(123);
		//Optional<Child> child = getChild();
		//child.ifPresentOrElse(c -> System.out.println(c), () ->{System.out.println("New Lambda Runnable");}); 
		//child.orElseThrow(NullPointerException::new);
		//int i=4;
		//int ia[][][] = new int[i][i=3][i];
		//System.out.println(ia.length+","+ia[0].length+","+ia[0][0].length);
		
		int[] intArray = new int[] {1,2,3,4,5};
		List<int[]> intList = Arrays.asList(intArray);
		intList.forEach(System.out::println);
		
		List<Integer> boxedIntList = IntStream.of(intArray).boxed().collect(Collectors.toList());
		boxedIntList.forEach(System.out::println);
		//System.out.println(QNA::getJavaEightMethodReference);
		Map<String, String> newMap = new HashMap<>();
		newMap.put("a", "5");newMap.put("b", "4");newMap.put("c", "3");newMap.put("d", "2");newMap.put("e", "1");
		newMap.entrySet().stream().sorted(Map.Entry.<String, String>comparingByValue()).forEach(System.out::println);
		Map<String, String> newMapSorted = newMap.entrySet().stream().sorted(Map.Entry.<String, String>comparingByValue()).collect(Collectors.toMap(e -> e.getKey(),  e -> e.getValue()));
		//newMapSorted.forEach((K,V) -> System.out.println(K+","+V));
		
		Employee emp1;
		List<Employee> empList = new ArrayList<>();
		emp1 = new Employee("Abhi1",10);
		empList.add(emp1);
		emp1 = new Employee("Abhi2",18);
		empList.add(emp1);
		emp1 = new Employee("Abhi2",25);
		empList.add(emp1);
		
		System.out.println("------- Filter ---------");
		empList.stream().filter(e -> e.getAge()>20).collect(Collectors.toList()).forEach(e-> System.out.println(e.getAge()));
		System.out.println("------- Reduce ---------");
		System.out.println(Stream.of(1,2,3,4,5,6).reduce(Integer::sum).get());
		
		Optional<Employee> optEmployee = Optional.of(new Employee());
		optEmployee.orElseThrow(() -> new RuntimeException("Employee Not Found "+123));
		
	}
	
	public static Predicate<Employee> isAdultEmployee(int age) {
		return e -> e.getAge() > age;
	}
	
	public static String getJavaEightMethodReference() {
		return "Hello Static Reference";
	}
	
	public static void f1(int i) {
		System.out.println("int "+i);
	}
	
	public static void f1(long i) {
		System.out.println("long "+i);
	}
	
	public static void f1(Integer i) {
		System.out.println("Integer "+i);
	}
}
