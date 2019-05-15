package com.abhi.java;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateDemo {
	static class Employee {
		private String name;
		private int age;
		
		public Employee(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		public String getName() {
			return this.name;
		}
		
		public int getAge() {
			return this.age;
		}
	}

	public static void main(String... args) {
		Predicate<Employee> empWithMoreThanEighteenAge = (emp) -> emp.getAge() >18;
		Predicate<Employee> empNameStartsWithAbhi = (emp) -> emp.getName().startsWith("Abhi");
		
		List.of(
					new Employee("Abhi",19),
					new Employee("Emp1",18),
					new Employee("Emp2", 19),
					new Employee("Abhi",18)).stream().
			filter(empWithMoreThanEighteenAge.and(empNameStartsWithAbhi)).
			collect(Collectors.toList()).forEach((e) ->System.out.println(e.getName()));
	}
}