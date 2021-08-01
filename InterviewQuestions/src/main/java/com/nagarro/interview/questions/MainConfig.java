package com.nagarro.interview.questions;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.abhi.interview.nagarro.config")
@Configuration
public class MainConfig {

	@Bean
	public Dependency1 d1() {
		return new Dependency1();
	}
	
	@Bean
	public Dependency2 d2() {
		return new Dependency2();
	}
	
	@Bean
	public Dependency3 d3() {
		return new Dependency3();
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		context.getBean(Holder.class);
	}
}
