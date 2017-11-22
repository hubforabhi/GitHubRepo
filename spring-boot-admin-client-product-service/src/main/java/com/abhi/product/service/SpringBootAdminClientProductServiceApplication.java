package com.abhi.product.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RequestMapping("/product")
public class SpringBootAdminClientProductServiceApplication {
	private static Logger logger = LoggerFactory.getLogger(SpringBootAdminClientProductServiceApplication.class);
	
    @Autowired
    private DiscoveryClient discoveryClient;
    
	@RequestMapping(value = "/ping")
	public ResponseEntity<String> ping() {
		logger.debug("SpringBootAdminClientProductServiceApplication.ping");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
    
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminClientProductServiceApplication.class, args);
	}
}
