package org.bgu.exception;

public class RegistrationRequiredException extends BguAuthenticationException {

	private static final long serialVersionUID = 1L;
	
	public RegistrationRequiredException(String msg, Throwable t) {
		super(msg, t);
	}

	public RegistrationRequiredException(String msg) {
		super(msg);
	}

	
}
