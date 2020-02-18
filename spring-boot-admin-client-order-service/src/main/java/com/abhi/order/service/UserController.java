package com.abhi.order.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.order.service.domain.User;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/find/{id}")
	public User findOne(String id) {
		logger.debug(id);
		return new User(id,"AA");
	}
	
	@GetMapping("/findAll")
	public List<User> findAll() {
		logger.debug("findAll");
		return Arrays.asList( 
		new User("1","AA"), new User("2","BB"), new User("3","CC"));
	}
	
	@GetMapping("/asyncFindAll")
	public Future<List<User>> asyncFindAll() {
		logger.debug("asyncFindAll");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new AsyncResult<List<User>>(Arrays.asList(new User("1","AA"), new User("2","BB"), new User("3","CC")));
	}
}