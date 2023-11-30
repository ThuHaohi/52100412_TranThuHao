package com.university.utilities;

import org.springframework.security.core.context.SecurityContextHolder;

public class JwtUtils {

	public static String getUsernameFromToken() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
}
