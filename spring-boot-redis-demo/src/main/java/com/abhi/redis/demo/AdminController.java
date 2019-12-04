package com.abhi.redis.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
public class AdminController {

	@GetMapping("/ping")
	public ResponseEntity<HttpStatus> ping() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
