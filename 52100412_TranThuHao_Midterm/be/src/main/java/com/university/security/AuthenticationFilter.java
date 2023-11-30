package com.university.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Value("${jwt.secret}")
	private String JWT_SECRET = "18D324ED7DBF09ED212DC8877760A4B4E1BB9028FC59507E8265019DB9B0ACBF";
	
	@Value("${jwt.expiry.hours}")
	private String JWT_EXPIRY_HOURS = "1";
	
	private AuthenticationManager authenticationManager;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager; 
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		return authenticationManager.authenticate(authenticationToken);
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain,
			Authentication authentication
	) throws IOException, ServletException
	{
		User user = (User) authentication.getPrincipal();
//		https://www.geeksforgeeks.org/stream-in-java/
//		List<String> roles = user.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.toList());
		
		Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());
		String access_token = JWT.create()
								.withSubject(user.getUsername())
								.withIssuer(request.getRequestURL().toString())
								.withIssuedAt(new Date())
								.withExpiresAt(new Date(System.currentTimeMillis() + Integer.parseInt(JWT_EXPIRY_HOURS) * 60 * 60 * 1000))
								.sign(algorithm);
//								.withClaim("roles", roles)
		
		String refresh_token = JWT.create()
									.withSubject(user.getUsername())
									.withIssuer(request.getRequestURL().toString())
									.withIssuedAt(new Date())
									.withExpiresAt(new Date(System.currentTimeMillis() + (Integer.parseInt(JWT_EXPIRY_HOURS) + 12) * 60 * 60 * 1000))
									.sign(algorithm);
									
//		response.setHeader("access_token", access_token);
//		response.setHeader("refresh_token", refresh_token);
		
		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", access_token);
		tokens.put("refresh_token", refresh_token);
		
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}
}
