package com.abhi.springbootswaggerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.abhi.controller"}) // without this line, controller wont be scanned to Spring context and swagger wont show up
public class SpringBootSwaggerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSwaggerDemoApplication.class, args);
	}
}
