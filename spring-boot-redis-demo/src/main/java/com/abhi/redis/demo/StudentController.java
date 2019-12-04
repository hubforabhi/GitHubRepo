package com.abhi.redis.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.redis.entity.Student;
import com.abhi.redis.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public List<Student> findAll() {
		return studentService.findAll();		
	}
	
	@GetMapping("/students/{id}")
	public Student findOne(@PathVariable Long id) {
		return studentService.findById(id)
	      .orElseThrow(() -> new RuntimeException());
	}
}