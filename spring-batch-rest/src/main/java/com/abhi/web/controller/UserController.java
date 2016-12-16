package com.abhi.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abhi.domain.view.UserView;

@RequestMapping("/user")
@Controller
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping("/list")
	public String getAllUser(Model model) {
		logger.debug("----- Getting All User -----------");
		List<UserView> userList = new ArrayList<>();
		userList.add(new UserView("fname1","lname1","city1"));
		userList.add(new UserView("fname2","lname2","city2"));
		model.addAttribute("userList", userList);
		model.addAttribute("message", "Hello World");
		return "listAllUser";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String errorHandler() {
		return "error";
	}
}
