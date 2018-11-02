package com.abhi.employee.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abhi.employee.model.Employee;

@Repository
public interface EmployeeRepository  extends CrudRepository<Employee, Long>{
	public Iterable<Employee> findByFirstName(String firstName);
}
