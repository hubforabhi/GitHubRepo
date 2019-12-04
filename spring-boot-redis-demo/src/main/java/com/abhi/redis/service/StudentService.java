package com.abhi.redis.service;

import java.util.List;
import java.util.Optional;

import com.abhi.redis.entity.Student;

public interface StudentService {
	public List<Student> findAll();
	public Optional<Student> findById(long id);
}
