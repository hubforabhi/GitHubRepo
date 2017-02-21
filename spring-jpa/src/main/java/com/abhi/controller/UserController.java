package com.abhi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> createUser() {
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
