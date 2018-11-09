package com.abhi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.model.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@GetMapping(path="/findOne/{id}")
	public ResponseEntity<Employee> findOne(@PathVariable("id") String empId) {
		Employee employee = new Employee(empId);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@GetMapping(path="/findAll")
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> empList = new ArrayList<>();
		Employee employee = new Employee();
		empList.add(employee);
		employee = new Employee();
		empList.add(employee);
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}

}