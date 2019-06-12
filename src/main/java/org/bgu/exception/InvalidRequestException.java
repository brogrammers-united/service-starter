package org.bgu.exception;

import org.springframework.http.HttpStatus;

/**
 * @author William Gentry
 */
public class InvalidRequestException extends BguException {

    public InvalidRequestException() {
        super("Invalid Request");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
