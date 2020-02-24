package com.abhi.spring.security.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.abhi.spring.security.demo.domain.view.UserView;

@Component("UserView")
public class UserViewValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserView.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fName", "UserView.fName.NotNull");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lName", "UserView.lName.NotNull");
	}
}