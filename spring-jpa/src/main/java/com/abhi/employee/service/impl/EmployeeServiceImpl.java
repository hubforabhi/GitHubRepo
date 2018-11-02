package com.abhi.employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.employee.model.Employee;
import com.abhi.employee.repository.EmployeeRepository;
import com.abhi.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	@Transactional
	public Employee findOne(Long empId) {
		return employeeRepository.findOne(empId);
	}
	
	@Override
	@Transactional
	public List<Employee> find() {
		List<Employee> empList = new ArrayList<>();
		employeeRepository.findAll().forEach( emp-> {
			empList.add(emp);
		});
		return empList;
	}
	
	@Override
	@Transactional
	public List<Employee> findByFirstName(String firstName) {
		List<Employee> empList = new ArrayList<>();
		employeeRepository.findByFirstName(firstName).forEach( emp-> {
			empList.add(emp);
		});
		return empList;
	}
}
