package com.abhi.order.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.abhi.order.service.security.JwtTokenAutheticationFilter;

//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
				.antMatchers("/admin/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin().disable()
				.logout().disable()
				.csrf().disable()
				.cors().disable()
			;
		http.addFilterBefore(jwtTokenAutheticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public JwtTokenAutheticationFilter jwtTokenAutheticationFilter() {
		return new JwtTokenAutheticationFilter();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User
				.withUsername("user")
				.password(passwordEncoder().encode("password"))
				.roles("USER")
				.build();
		
		UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		userDetailsManager.createUser(user);
		return userDetailsManager;
	}
}