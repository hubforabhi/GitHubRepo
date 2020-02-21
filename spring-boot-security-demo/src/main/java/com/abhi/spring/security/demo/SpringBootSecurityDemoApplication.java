package com.abhi.spring.security.demo;

import java.util.Arrays;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.abhi.spring.security.demo.domain.view.UserView;
import com.abhi.spring.security.demo.validator.UserViewValidator;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(SpringBootSecurityDemoApplication.class);
	
	@Autowired
	private Environment env;
	
	@Bean
	public CommandLineRunner initRunner() {
		return (args) -> {
			MessageSource messageSource = messageSource();			
			logger.debug("SpringBootSecurityDemoApplication.initRunner "+env.getDefaultProfiles()[0]);
			MessageConstants message = MessageConstants.LOGIN_FAILED;
			logger.debug("SpringBootSecurityDemoApplication.initRunner "+message.getMessage());
			logger.debug("SpringBootSecurityDemoApplication.initRunner "+
					messageSource.getMessage("application.login.failed", new Object[] {"abhi bhowmick" }, Locale.US));
		};
	}
	
	@Bean
	public MessageSource messageSource() {
		AbstractResourceBasedMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:ValidationMessages");
		return messageSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}

	public static void main(String[] args) {
		SpringApplication springApp = new SpringApplication(SpringBootSecurityDemoApplication.class);
		springApp.setBannerMode(Banner.Mode.OFF);
		//ConfigurableApplicationContext appContext = 
				springApp.run(args);
		/*UserViewValidator userViewValidator = appContext.getBean(UserViewValidator.class);		
		UserView userView = new UserView("1234");		
		
		Errors errors = new BeanPropertyBindingResult(userView, UserView.class.getName());
		AbstractBindingResult bindingResult = (AbstractBindingResult)errors;
	    	DefaultMessageCodesResolver messageCodesResolver =
	    			(DefaultMessageCodesResolver) bindingResult.getMessageCodesResolver();
	    messageCodesResolver.setMessageCodeFormatter(DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
	    ValidationUtils.invokeValidator(userViewValidator, userView, errors);
	    bindingResult.getAllErrors().forEach(e -> System.out.println(Arrays.toString(e.getCodes())));*/
	}
}