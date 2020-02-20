package com.abhi.spring.security.demo.validator;

import org.springframework.stereotype.Component;

import com.abhi.spring.security.demo.domain.view.UserView;

@Component
public class UserValidator implements Validator<UserView> {
	public boolean validate(UserView t) {
		return false;
	}
}
