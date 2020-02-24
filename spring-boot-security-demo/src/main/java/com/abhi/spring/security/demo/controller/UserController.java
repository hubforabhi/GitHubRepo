package com.abhi.spring.security.demo.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.AbstractPropertyBindingResult;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
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

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	@Qualifier("UserView")
	private Validator validator;
	
	@Autowired
	private ApplicationContext appContext;

	@GetMapping("/findOne/{id}")
	public ResponseEntity<UserView> fineOne(@PathVariable("id") String id) {
		UserView userView = new UserView(id);		
		//validator.validate(userView);
		return new ResponseEntity<UserView>(userService.createUser(userView), HttpStatus.OK);
	}
	
	@PostMapping("/saveOne")//First way to validate
	public ResponseEntity<UserView> saveOne(@Valid 	@RequestBody UserView userView) {		
		//@Valid will throw MethodArgumentNotValidException if validation fails, below ExceptionHandler will translate error and throw HTTP 403		
		//No Need to declare a separate validator class or implementation, hence no autowiring validator candidate
		return new ResponseEntity<UserView>(userService.createUser(userView), HttpStatus.OK);
	}
	
	@PostMapping("/saveTwo")//Second way to validate
	public ResponseEntity<?> saveTwo(@RequestBody UserView userView) {	
		AbstractPropertyBindingResult errors = new BeanPropertyBindingResult(userView, userView.getClass().getName());
		//UserViewValidator userViewValidator = new UserViewValidator();
		//userViewValidator.validate(userView, bindingResult);
		ValidationUtils.invokeValidator(validator, userView, errors);
		if(errors.hasFieldErrors()) {
			Map<String, String> errorsMap = new HashMap<>();
			errors.getFieldErrors().forEach((e) -> {
				errorsMap.put(e.getField(), 
				appContext.getMessage(e.getCode(), null, Locale.getDefault()));
			});	
			return new ResponseEntity<Map<String, String>>(errorsMap, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<UserView>(userService.createUser(userView), HttpStatus.OK);			
		}
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