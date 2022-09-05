package com.solmi.shorket.global.exception;

public class UserAlreadyDeletedCException extends RuntimeException {

    public UserAlreadyDeletedCException() {
        super();
    }

    public UserAlreadyDeletedCException(String message) {
        super(message);
    }

    public UserAlreadyDeletedCException(String message, Throwable cause) {
        super(message, cause);
    }
}
