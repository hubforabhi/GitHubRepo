package com.abhi.hystrix.controller;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/admin")
public class AdminController {

		@GetMapping("/ping")
		public ResponseEntity<Void> ping() {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		@HystrixCommand(fallbackMethod = "cascadeFallback",
				commandProperties = {
						@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
						@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
				})
		@GetMapping("/cascade")
		public ResponseEntity<String> cascade() {
			Random boolRandom = new Random();
			if(boolRandom.nextBoolean())
				return new ResponseEntity<String>("cascade : success",HttpStatus.OK);
			else 
				throw new RuntimeException("Error while calling service");
		}
		
		public ResponseEntity<String> cascadeFallback() {
			return new ResponseEntity<String>("cascadeFallback : success", HttpStatus.OK);
		}
}