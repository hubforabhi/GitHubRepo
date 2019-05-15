package com.abhi.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
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
	
	private static void demoListThread() { //Let's see same thread read and write will throw ConcurrentModificationException or not?
		List<Integer> strList = new ArrayList<>();strList.add(1);strList.add(2);strList.add(3);strList.add(4);strList.add(5);strList.add(6);
		System.out.println("------- By Iterator ---------");
		new Thread() {
			public void run() {
				Iterator<Integer> itr = strList.iterator();
				var i=0;
				while(itr.hasNext()) {
					if(i==2) break;
					System.out.print(itr.next()); i++;
				}
				strList.add(7);
				while(itr.hasNext()) { //this will throw ConcurrentModificatonException
					System.out.print(itr.next());
				}
			}
		}.start();
		List<Integer> str2List = new ArrayList<>();str2List.add(1);str2List.add(2);str2List.add(3);str2List.add(4);str2List.add(5);str2List.add(6);
		System.out.println("------- By Direct Access ---------");
		new Thread() {
			public void run() {
				for(int i=0;i<str2List.size();i++) {
					System.out.print(str2List.get(i));
				}
				str2List.add(7);
				for(int i=0;i<str2List.size();i++) {
					System.out.print(str2List.get(i));
				}
			}
		}.start();;
		
		Callable<Integer> callable = () -> {			
				System.out.println("Lambda Runnable");
				return 1;
		};
		ExecutorService exeService = Executors.newFixedThreadPool(2);				
		try {
			exeService.execute(() -> System.out.println("Inline Runnable Execute ")); 
			Future<?> future = exeService.submit(() ->System.out.println("Inline Runnable Submit"));
			System.out.println(future.get()); //returns null
			Future<Integer> intFuture = exeService.submit(()-> 1+1);
			System.out.println(intFuture.get());
			List<Callable<String>> listOfCallableTasks = Arrays.asList(()-> {return "1";}, ()-> {return "2";}, ()-> {return "3";});
			List<Future<String>> listOfFutureStrings = exeService.invokeAll(listOfCallableTasks);
			List<String> listOfStrings = new ArrayList<>();
			listOfFutureStrings.forEach((e) -> {
				try {
					listOfStrings.add(e.get());
				} catch (InterruptedException | ExecutionException e1) {
					e1.printStackTrace();
				}
			}); 
			listOfStrings.stream().map(Integer::parseInt).reduce(Integer::sum).get();
			exeService.awaitTermination(500, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			exeService.shutdown();
		}
	}
	
	private static Predicate<Employee> employeeAgeGreaterThan25() {
		return e-> e.getAge() >20;
	}
	
	private static Predicate<Employee> employeeAgeGreaterThan(int age) {
		return e-> e.getAge() > age;
	}
	
	public static void main(String []args) {
		Supplier<String> getRandomString = () -> {
			return "Random 123 String";
		};
		
		Consumer<String> acceptConsumer = (value) -> {
			System.out.println(value);
		};
		
		acceptConsumer.accept(getRandomString.get());
		
		Predicate<Employee> empPrd1 = emp -> emp.getAge()>21;
		Predicate<Employee> empPrd2 = emp -> emp.getAge()>18;
		
		empPrd1.and(empPrd2);
		
	}
	
	private static<T> void getStream(List<T> list, T[] arr) {
		 
	}
	
	public static void main1(String []args) {		
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
		empList.stream().filter(e -> e.getAge()>20).collect(Collectors.toList()).forEach(e-> System.out.print(e.getName() +"::"+e.getAge()));
		System.out.println("\n------- Filter With Predicate ---------");
		empList.stream().filter(employeeAgeGreaterThan25()).collect(Collectors.toList()).forEach(e-> System.out.print(e.getName() +"::"+e.getAge()));
		System.out.println("\n------- Filter With Predicate Parameter ---------");
		empList.stream().filter(employeeAgeGreaterThan(15)).collect(Collectors.toList()).forEach(e-> System.out.print(e.getName() +"::"+e.getAge()));
		System.out.println("\n------- Map ---------");
		empList.stream().map(e-> e.getAge()>20).collect(Collectors.toList()).forEach(System.out::print);
		System.out.println("\n------- Mapper Function ---------");
		empList.stream().map(e-> { return e;}).collect(Collectors.toList()).forEach(e-> System.out.print(e.getName() +"::"+e.getAge()));
		System.out.println("\n------- Reduce ---------");
		System.out.println(Stream.of(1,2,3,4,5,6).reduce(Integer::sum).get());
		System.out.println("------- Map and Reduce ---------");
		System.out.println(Stream.of("1","2","3","4","5","6","7","8","9","10").map(Integer::parseInt).filter(e-> e%2==0).reduce(Integer::sum).get());
		
		Optional<Employee> optEmployee = Optional.of(new Employee());
		optEmployee.orElseThrow(() -> new RuntimeException("Employee Not Found "+123));
		String message = Optional.of("Optional Hello World").orElse("Optional orElse Hello World");
		System.out.println(message);
		
		//Cognizant Question
		Map<Employee,String> empMap = new TreeMap<>();
		Employee empKey1 = new Employee("Abhi5",10);
		empMap.put(empKey1, "5");
		empKey1 = new Employee("Abhi4", 20);
		empMap.put(empKey1, "4");
		empKey1 = new Employee("Abhi3", 30);
		empMap.put(empKey1, "3");
		empKey1 = new Employee("Abhi2", 40);
		empMap.put(empKey1, "2");
		empKey1 = new Employee("Abhi1", 50);
		empMap.put(empKey1, "1");
		empMap.keySet().forEach(e-> System.out.println(e.getName() +"::" +e.getAge()));
		empMap.values().forEach(System.out::println);
		System.out.println("------- Same Thread Modification ---------");
		demoListThread();
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
