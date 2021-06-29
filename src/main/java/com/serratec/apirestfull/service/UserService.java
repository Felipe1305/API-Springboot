package com.serratec.apirestfull.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.serratec.apirestfull.security.UserSS;

@Service
public class UserService {
 public static UserSS authenticated( ) {
	 
	 try {
		 return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
	} catch (Exception e) {
		return null;
	}
 }
}
