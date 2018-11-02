package com.abhi.department.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abhi.department.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
