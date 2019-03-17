package org.bgu.exception;

public class InvalidAuthenticationRequestFormatException extends BguAuthenticationException {

	private static final long serialVersionUID = 1L;
	
	public InvalidAuthenticationRequestFormatException() {
		super("Authentication request is in invalid format");
	}

	public InvalidAuthenticationRequestFormatException(String msg, Throwable t) {
		super(msg, t);
	}

	public InvalidAuthenticationRequestFormatException(String msg) {
		super(msg);
	}

	
}
