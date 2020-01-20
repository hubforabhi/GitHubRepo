package com.abhi.cucumber.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/admin") // necesary to set sub content root as "admin"
public class SpringBootCucumberApplicationDemo {

	@GetMapping("/ping")
	public ResponseEntity<HttpStatus> ping() {
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCucumberApplicationDemo.class, args);
	}
}
