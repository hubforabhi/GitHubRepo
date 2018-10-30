package com.abhi.feign.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.Feign;

@RestController
@RequestMapping("/feign")
public class FeignController {
	private static Logger logger = LoggerFactory.getLogger(FeignController.class);
	
	@RequestMapping(value = "/ping")
	public ResponseEntity<String> ping() {
		ProductServiceClient productServiceClient = 
				Feign.builder().target(ProductServiceClient.class, "http://product-service/product/ping");
		productServiceClient.ping();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
