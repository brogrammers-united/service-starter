package org.bgu.exception;

import org.springframework.security.core.AuthenticationException;

public abstract class BguAuthenticationException extends AuthenticationException {
	
	private static final long serialVersionUID = 1L;

	public BguAuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}

	public BguAuthenticationException(String msg) {
		super(msg);
	}

	
}
