package com.abhi.spring.security.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.spring.security.demo.domain.view.UserView;
import com.abhi.spring.security.demo.service.UserService;
import com.abhi.spring.security.demo.validator.Validator;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private Validator<UserView> validator;

	@GetMapping("/findOne/{id}")
	public ResponseEntity<UserView> fineOne(@PathVariable("id") String id) {
		UserView userView = new UserView(id);		
		validator.validate(userView);
		return new ResponseEntity<UserView>(userService.createUser(userView), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<UserView> save(@Valid @RequestBody UserView userView) {
		//@Valid will throw MethodArgumentNotValidException if validation fails,hence below ExceptionHandler
		//validator.validate(userView);
		return new ResponseEntity<UserView>(userService.createUser(userView), HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
