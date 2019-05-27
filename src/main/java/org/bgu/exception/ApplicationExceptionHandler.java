package org.bgu.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	private final Logger logger = LogManager.getLogger(getClass());

	/*
	 * Catches the BguAuthenticationException, response with appropriate status and provided error message
	 */
	@ExceptionHandler(BguException.class)
	public ResponseEntity<Map<String, String>> handleAuthenticationException(BguException e) {
		logger.warn("Handling exception {}: ", e.getClass().getName());
		return ResponseEntity.status(e.getHttpStatus()).contentType(MediaType.APPLICATION_JSON_UTF8).body(Collections.singletonMap("error", e.getMessage()));
	}
}
