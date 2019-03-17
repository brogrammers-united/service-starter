package org.bgu.exception;

public class InvalidClientRegistrationException extends BguAuthenticationException {

	private static final long serialVersionUID = 1L;

	public InvalidClientRegistrationException() {
		this("Invalid client");
	}
	
	public InvalidClientRegistrationException(String msg, Throwable t) {
		super(msg, t);
	}

	public InvalidClientRegistrationException(String msg) {
		super(msg);
	}

	
}
