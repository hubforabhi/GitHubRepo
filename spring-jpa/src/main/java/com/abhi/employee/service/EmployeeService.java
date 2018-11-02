package com.abhi.employee.service;

import java.util.List;

import com.abhi.employee.model.Employee;

public interface EmployeeService {
	
	public Employee findOne(Long empId);
	public List<Employee> find();
	public List<Employee> findByFirstName(String firstName);
}
