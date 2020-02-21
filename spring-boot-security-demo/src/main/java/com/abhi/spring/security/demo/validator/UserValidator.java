package com.abhi.spring.security.demo.validator;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.ValidationUtils;

import com.abhi.spring.security.demo.domain.view.UserView;

@Component
public class UserValidator implements Validator<UserView> {
	private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean validate(UserView userView) {
		AbstractBindingResult errors = new BeanPropertyBindingResult(userView, UserView.class.getName());
		ValidationUtils.rejectIfEmpty(errors, "fName", "user.name.empty");
		DefaultMessageCodesResolver messageCodesResolver =
	              (DefaultMessageCodesResolver) errors.getMessageCodesResolver();
		//messageCodesResolver.setMessageCodeFormatter(DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
		errors.getAllErrors().forEach(e ->		
		logger.debug("UserValidator.validate user fname empty " + messageSource.getMessage(e.getCode(), null, Locale.US)));
		return false;		
	}
}