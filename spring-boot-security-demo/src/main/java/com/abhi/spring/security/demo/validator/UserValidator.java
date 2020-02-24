package com.abhi.spring.security.demo.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.abhi.spring.security.demo.domain.view.UserView;

@Component
public class UserValidator implements Validator<UserView> {
	private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);
	
	@Override
	public boolean validate(UserView userView) {
		logger.debug("UserValidator.validate user fname empty ");
		return false;		
	}
}