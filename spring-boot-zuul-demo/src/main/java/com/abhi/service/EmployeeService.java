package com.abhi.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.abhi.model.Employee;

@Service
public class EmployeeService {

	@Cacheable(cacheNames="employee-cache", key="#empId", condition="#manager == true")	
	public Employee getEmployee(String empId, boolean manager) {
		return new Employee();
	}
}
