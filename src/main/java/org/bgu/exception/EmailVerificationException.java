package org.bgu.exception;

public class EmailVerificationException extends BguAuthenticationException {

	private static final long serialVersionUID = 1L;

	public EmailVerificationException() {
		super("You must verify your email before continuing");
	}
	
	public EmailVerificationException(String msg, Throwable t) {
		super(msg, t);
	}

	public EmailVerificationException(String msg) {
		super(msg);
	}

	
}
