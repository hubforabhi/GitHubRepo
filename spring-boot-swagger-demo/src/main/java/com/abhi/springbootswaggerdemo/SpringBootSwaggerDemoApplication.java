package com.abhi.springbootswaggerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfig.class) // without this import swagger will not enabled , hence swagger ui wont show the api details
@ComponentScan(basePackages= {"com.abhi.employee.controller"}) // without this line, controller wont be scanned to Spring context and swagger wont show up
public class SpringBootSwaggerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSwaggerDemoApplication.class, args);
	}
}
