package com.nnk.springboot.execptions;

import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception handling class
 * @author maure
 *
 */
@ResponseStatus
public class RessourceNotFoundException extends RuntimeException {
	
	public RessourceNotFoundException(String message) {
		super(message);
		
	}
	public RessourceNotFoundException(String message, Throwable throwable) {
		super(message,throwable);
	}

}
