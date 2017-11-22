package com.abhi.product.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
	
	@RequestMapping("/")
	public ResponseEntity<HttpStatus> defaultAddress() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}