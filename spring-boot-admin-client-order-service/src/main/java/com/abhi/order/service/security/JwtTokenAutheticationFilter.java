package com.abhi.order.service.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;

public class JwtTokenAutheticationFilter extends OncePerRequestFilter {
	
	@Value("{jwt.secret:TokenSecret}")
	private String jwtSecret;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws("").getBody().getSubject();
		//UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		//UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				//userDetails, null, userDetails.getAuthorities());
		//SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}
}