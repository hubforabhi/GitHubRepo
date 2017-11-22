package com.abhi.springbootdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.codecentric.boot.admin.config.EnableAdminServer;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableAdminServer
@RestController
@RequestMapping("/admin")
public class SpringBootAdminServerApplication {
	private static Logger logger = LoggerFactory.getLogger(SpringBootAdminServerApplication.class);
	
	@RequestMapping(value = "/ping")
	public ResponseEntity<String> ping() {
		logger.debug("SpringBootAdminServerApplication.ping");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Configuration
	public static class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
			http.logout().logoutUrl("/logout");
			http.csrf().disable();
			http.authorizeRequests()
			.antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**")
			.permitAll();
			// ... and any other request needs to be authorized
			http.authorizeRequests().antMatchers("/**").authenticated();
			http.httpBasic();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminServerApplication.class, args);
	}
}
