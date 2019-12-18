package com.abhi.feign.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class FeignController {
	private static Logger logger = LoggerFactory.getLogger(FeignController.class);
	
	@Autowired
	private ProductServiceClient productServiceClient;
	
	@RequestMapping(value = "/ping")
	public ResponseEntity<String> ping() {
		logger.debug("FeignController.ping()");
		//ProductServiceClient productServiceClient = // Not working as of now as product-service cant be resolved hostname
				//Feign.builder().target(ProductServiceClient.class, "http://product-service/product/");
		productServiceClient.ping();
		String response = 
		productServiceClient.find()+
		productServiceClient.findAll();
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
