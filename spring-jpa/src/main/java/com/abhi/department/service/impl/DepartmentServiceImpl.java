package com.abhi.department.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.department.model.Department;
import com.abhi.department.repository.DepartmentRepository;
import com.abhi.department.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	@Transactional
	public Department findOne(Long id) {
		return departmentRepository.findOne(id);
	}

}
