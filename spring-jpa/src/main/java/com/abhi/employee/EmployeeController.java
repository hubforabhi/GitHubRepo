package com.abhi.employee;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.employee.model.Employee;
import com.abhi.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<Employee> findOne(@PathVariable("id") Long id) {
		logger.debug("EmployeeController.findOne id :"+id);
		Employee employee = employeeService.findOne(id);
		logger.debug("EmployeeController.findOne first name  :"+employee.getFirstName());
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@GetMapping("/find/")
	public ResponseEntity<List<Employee>> find() {
		logger.debug("EmployeeController.find");
		List<Employee> empList = employeeService.find();
		logger.debug("EmployeeController.find size  :"+empList.size());
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}
	
	@GetMapping("/findByFirstName/{firstName}")
	public ResponseEntity<List<Employee>> findByFirstName(@PathVariable("firstName") String firstName) {
		logger.debug("EmployeeController.findByFirstName firstName " + firstName);
		List<Employee> empList = employeeService.findByFirstName(firstName);
		logger.debug("EmployeeController.findByFirstName size  :"+empList.size());
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}
}
