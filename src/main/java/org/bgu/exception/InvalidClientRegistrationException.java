package org.bgu.exception;

import org.springframework.http.HttpStatus;

/**
 * @author William Gentry
 */
public class InvalidClientRegistrationException extends BguException {

    public InvalidClientRegistrationException() {
        super("Failed to locate requested OAuth2 Client");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
