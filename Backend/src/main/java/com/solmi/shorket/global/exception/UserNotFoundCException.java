package com.solmi.shorket.global.exception;

public class UserNotFoundCException extends RuntimeException {

    public UserNotFoundCException() {
        super();
    }

    public UserNotFoundCException(String message) {
        super(message);
    }

    public UserNotFoundCException(String message, Throwable cause) {
        super(message, cause);
    }
}
