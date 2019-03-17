package org.bgu.exception;

public class FailedAuthenticationAttemptException extends BguAuthenticationException {

	private static final long serialVersionUID = 1L;

	public FailedAuthenticationAttemptException() {
		this("Invalid Credentials");
	}
	
	public FailedAuthenticationAttemptException(String msg, Throwable t) {
		super(msg, t);
	}

	public FailedAuthenticationAttemptException(String msg) {
		super(msg);
	}

	
}
