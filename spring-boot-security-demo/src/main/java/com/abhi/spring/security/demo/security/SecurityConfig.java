package com.abhi.spring.security.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
		return new JwtAuthenticationTokenFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			cors().and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
				.antMatchers("/admin/**").permitAll() // bypass security for all admin URL
				.antMatchers("/auth/**").permitAll() // bypass security for all admin URL
				.antMatchers("/user/**").authenticated() // apply authentication on all URL prefix with /user
				.anyRequest().authenticated()
			.and()
				.formLogin().disable()
				.logout().disable();

		http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inMemoryUserDetailsManager());
	}
	
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    	UserDetails theAdmin = User.withUsername("admin")
                .password(passwordEncoder().encode("password")).roles("ADMIN").build();

    	UserDetails theUser = User.withUsername("user")    			
    			.password(passwordEncoder().encode("password")).roles("USER").build();
    	
    	InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        
        userDetailsManager.createUser(theAdmin);
        userDetailsManager.createUser(theUser);
        
        return userDetailsManager;
    }
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception { //AuthController needs this bean
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}