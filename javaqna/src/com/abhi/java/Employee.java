package com.abhi.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Employee implements Serializable {
	private String name;
	private Address[] address = new Address[2];
	private int age;

	public Employee() {
		System.out.println("Employee Default Constructor ");
	}
	
	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Employee(String name, Address address) {
		System.out.println("Employee Param Constructor "+ name);
		this.name = name;
		this.address[0] = address;
		this.address[1] = new Address("","","Hatira",700157);
	}
	
	public int getAge() {
		return this.age;
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
	
	public Address getAddress() {
		return address[0];
	}
	
	public Address getAnotherAddress() {
		return address[1];
	}
	
	public static void main(String[] args) {
		Employee emp1 = new Employee("abhi"+"1", new Address("","","Saltlake",700091));
		try(FileOutputStream fos =new FileOutputStream("employee.ser")) {
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(emp1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(FileInputStream fis = new FileInputStream("employee.ser")) {
			ObjectInputStream ois = new ObjectInputStream(fis);
			Employee employee = (Employee) ois.readObject();
			System.out.println(employee.getName());
			System.out.println(employee.getAddress().getCity());
			System.out.println(employee.getAnotherAddress().getCity());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*String eName = "Abhi Bhowmick";
		Employee emp1 = new Employee(eName+"1");
		Employee emp2 = new Employee(eName+"2");
		
		Map<Employee, String> empName = new HashMap<>();
		empName.put(emp1, emp1.getName());
		System.out.println("First Key Placed");
		empName.put(emp2, emp2.getName());
		System.out.println("Second Key Placed");
		
		System.out.println(empName.get(emp1));
		System.out.println(empName.get(emp2));*/
	}
}
