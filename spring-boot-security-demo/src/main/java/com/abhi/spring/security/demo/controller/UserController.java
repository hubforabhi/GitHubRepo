package com.abhi.spring.security.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.spring.security.demo.model.User;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<User> fineOne(@PathVariable("id") String id) {
		User user = new User(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
