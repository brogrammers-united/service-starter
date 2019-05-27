package org.bgu.exception;

import org.springframework.http.HttpStatus;

/**
 * @author William Gentry
 */
public class EmailNotFoundException extends BguException {

    public EmailNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
