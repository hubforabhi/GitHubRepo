package com.abhi.redis.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.redis.entity.Student;
import com.abhi.redis.service.StudentService;

@RestController
public class StudentController {
	private Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public List<Student> findAll() {
		List<Student> list = studentService.findAll();
		logger.debug("StudentController :: "+list.size() );
		return list;		
	}
	
	@GetMapping("/students/{id}")
	public Student findOne(@PathVariable Long id) {
		logger.debug("StudentController/{id} id :: "+id);
		return studentService.findById(id)
	      .orElseThrow(() -> new RuntimeException());
	}
}