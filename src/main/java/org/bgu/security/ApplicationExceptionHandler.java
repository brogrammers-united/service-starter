package org.bgu.security;

import java.util.Collections;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bgu.config.LoggerLevel;
import org.bgu.exception.BguAuthenticationException;
import org.bgu.exception.FailedAuthenticationAttemptException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ApplicationExceptionHandler {
	
	private final Logger logger = LogManager.getLogger(getClass());

	/*
	 * Catches the BguAuthenticationException, response with appropriate status and provided error message
	 */
	@ExceptionHandler(BguAuthenticationException.class)
	public ResponseEntity<Map<String, String>> handleAuthenticationException(BguAuthenticationException e) {
		logger.log(LoggerLevel.SECURITY, "Handling exception {}: ", e.getClass().getName());
		return ResponseEntity.status(determineHttpStatus(e)).contentType(MediaType.APPLICATION_JSON_UTF8).body(Collections.singletonMap("message", e.getMessage()));
	}
	
	/*
	 * Helper method to personalize status code for each application specific exception 
	 */
	private final HttpStatus determineHttpStatus(BguAuthenticationException e) {
		if (e instanceof FailedAuthenticationAttemptException)
			return HttpStatus.UNAUTHORIZED; 
		return HttpStatus.BAD_REQUEST;
	}
}
