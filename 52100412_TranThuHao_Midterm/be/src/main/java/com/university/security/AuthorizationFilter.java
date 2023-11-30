package com.university.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class AuthorizationFilter extends OncePerRequestFilter {
	
	@Value("${jwt.secret}")
	private String JWT_SECRET = "18D324ED7DBF09ED212DC8877760A4B4E1BB9028FC59507E8265019DB9B0ACBF";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/api/login")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String token = authorizationHeader.substring("Bearer ".length());
			Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());
			JWTVerifier jwtVerifier = JWT.require(algorithm).build();
			try {
				DecodedJWT decodedToken = jwtVerifier.verify(token);
				String username = decodedToken.getSubject();
//				String[] roles = decodedToken.getClaim("roles").asArray(String.class);
				
				Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//				for (String r : roles) {
//					authorities.add(new SimpleGrantedAuthority(r));
//				}
				
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				filterChain.doFilter(request, response);
			} catch (Exception e) {
				response.setStatus(403);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}
}
