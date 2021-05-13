package com.nnk.springboot.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserToken {
	public static String  getUserFromToken() {
	String username;
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
	if (principal instanceof UserDetails) {
	   username = ((UserDetails)principal).getUsername();
	} else {
	   username = principal.toString();
	}
	return username;
}

}
