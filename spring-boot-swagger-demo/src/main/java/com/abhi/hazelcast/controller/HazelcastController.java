package com.abhi.hazelcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;

//@RestController
//@RequestMapping("/hazelcast")
public class HazelcastController {
	
	@Autowired
	private HazelcastInstance instance; 

	@GetMapping("/write/{message}")
	public ResponseEntity<String> write(@PathVariable("message") String message) {
		instance.getMap("messages").put("message", message);
		return new ResponseEntity<String>("Successful", HttpStatus.OK);
	}
	
	@GetMapping("/read")
	public ResponseEntity<String> read() {
		return new ResponseEntity<String>(instance.getMap("messages").get("message").toString(), HttpStatus.OK);
	}
	
	@GetMapping("/ping")
	public ResponseEntity<Void> ping() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}