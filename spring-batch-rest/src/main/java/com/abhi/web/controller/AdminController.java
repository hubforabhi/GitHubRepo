package com.abhi.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.gateway.RequestProcessorGateway;

@RestController
@RequestMapping("/admin")
public class AdminController {
	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
    
	@Autowired
	private RequestProcessorGateway gateway;
	
	@RequestMapping(method = RequestMethod.GET, value = "/ping")
	public String test() {
		logger.debug("GET Method test");
		return "Hello World";
	}	
	
	@RequestMapping(method = RequestMethod.POST, value = "/fireTestJob")
	public String fireTestJob() {
		logger.debug("POST Method fireTestJob");
		//eventBus.notify("BatchRequestEvent", Event.wrap("Hello World from Spring Boot Reactor"));
		gateway.processRequest("Hello World from Spring Boot Reactor");
		return "Test Job Fired Successfully";
	}
}