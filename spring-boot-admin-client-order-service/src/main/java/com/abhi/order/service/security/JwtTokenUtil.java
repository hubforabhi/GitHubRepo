package com.abhi.order.service.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {
	
	@Value("{jwt.secret:TokenSecret}")
	private String jwtSecret;
	
	public String generateToken(Authentication authentication) {
		return Jwts.builder().signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
}