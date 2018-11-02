package com.abhi.java;

import java.util.HashMap;
import java.util.Map;

public class Employee {
	private String name;
	
	public Employee(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		System.out.println("-- hashCode --");
		return 12345;//name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("-- equals --");
		/*Employee empObj = (Employee) obj;
		if(this == obj) {
			return true;
		} else if (this.name.equals(empObj.getName())) {
			return true;
		} else if(this.name == empObj.getName()) {
			return true;
		}
		System.out.println("-- equals -- "+this.getName());*/		
		return false;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		String eName = "Abhi Bhowmick";
		Employee emp1 = new Employee(eName+"1");
		Employee emp2 = new Employee(eName+"2");
		
		Map<Employee, String> empName = new HashMap<>();
		empName.put(emp1, emp1.getName());
		System.out.println("First Key Placed");
		empName.put(emp2, emp2.getName());
		System.out.println("Second Key Placed");
		
		System.out.println(empName.get(emp1));
		System.out.println(empName.get(emp2));
	}
}
