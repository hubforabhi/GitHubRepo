package com.abhi.spring.security.demo;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;

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
		messageSource.setBasename("classpath:messages");
		return messageSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}
}