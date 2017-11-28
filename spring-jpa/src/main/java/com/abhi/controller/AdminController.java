package com.abhi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.bus.Event;
import reactor.bus.EventBus;

@RestController
@RequestMapping("/admin")
public class AdminController {
	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
    @Autowired
    private EventBus bus;
	
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
	public ResponseEntity<HttpStatus> ping() {
		bus.notify("doAudit", Event.wrap("Audit Message!"));
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}	
}