package com.abhi.springbootswaggerdemo;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	//@Bean // default implementation
	public Docket defaultApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				//.apis(RequestHandlerSelectors.any())  // this will enable all possible endpoint even from spring core itself            
		        //.paths(PathSelectors.any())     		//like error , spring's own controller and etc.
				.apis(RequestHandlerSelectors.basePackage("com.abhi.employee.controller"))
				.paths(regex("/employee.*"))
				.build();
	}
	
	@Bean // Custom implementation
	public Docket customApi() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.abhi.employee.controller"))
	      .paths(PathSelectors.ant("/employee/**")) // this pattern is important , without double star findOne with parameter is not visible
	      .build()
	      .apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	     return new ApiInfo(
	       "Employe Service REST API", 
	       "Employee Operation Specific REST endpoints guarded with JWT", 
	       "V1.1", 
	       null, 
	       null, 
	       null, null, Collections.emptyList());
	}
}
