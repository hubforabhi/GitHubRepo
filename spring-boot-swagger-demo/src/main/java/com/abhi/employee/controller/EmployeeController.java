package com.abhi.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.employee.model.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/employee")
@Api(value="")
public class EmployeeController {

	@ApiOperation(value="", response=Employee.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    	}
    )
	
	@GetMapping(path="/findOne/{id}", produces = "application/json")	
	public ResponseEntity<Employee> findOne(@PathVariable("id") String empId) {
		Employee employee = new Employee(empId);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@GetMapping(path="/findAll")
	@ApiOperation(value="", response=Employee.class, produces = "application/json", responseContainer = "List")
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> empList = new ArrayList<>();
		Employee employee = new Employee();
		empList.add(employee);
		employee = new Employee();
		empList.add(employee);
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}

}