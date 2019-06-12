package org.bgu.exception;

import org.springframework.http.HttpStatus;

/**
 * @author William Gentry
 */
public abstract class BguException extends RuntimeException {

    private final String message;

    public BguException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public abstract HttpStatus getHttpStatus();
}
