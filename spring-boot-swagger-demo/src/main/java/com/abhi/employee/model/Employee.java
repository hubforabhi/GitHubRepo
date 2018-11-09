package com.abhi.model;

/*
 * lombok annotation will show up an error message like 'No converter found for return value of type : Employee ' 
 */

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Employee {	
	private String empId;
	private static int serialEmployeeId = 1;
	
	public Employee() {
		empId = ""+serialEmployeeId++;
	}
	
	public Employee(String empId) {
		this.empId = empId;
	}
	
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}


}
