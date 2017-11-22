package com.abhi.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@RestController
@RequestMapping("/zuul")
public class SpringBootZuulDemoApplication {
	private static Logger logger = LoggerFactory.getLogger(SpringBootZuulDemoApplication.class);
    
	@RequestMapping(value = "/ping")
	public ResponseEntity<String> ping() {
		logger.debug("SpringBootZuulDemoApplication.ping");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootZuulDemoApplication.class, args);
	}
}