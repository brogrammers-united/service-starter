package org.bgu.exception;

public class EmailNotFoundException extends BguAuthenticationException {

	private static final long serialVersionUID = 1L;

	public EmailNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

	public EmailNotFoundException(String msg) {
		super(msg);
	}
	
}
