package com.solmi.shorket.global.exception;

public class UserSignupFailedCException extends RuntimeException {

    public UserSignupFailedCException() {
        super();
    }

    public UserSignupFailedCException(String message) {
        super(message);
    }

    public UserSignupFailedCException(String message, Throwable cause) {
        super(message, cause);
    }
}
