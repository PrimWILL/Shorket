package com.solmi.shorket.global.exception;

public class EmailAlreadyExistCException extends RuntimeException {

    public EmailAlreadyExistCException() {
        super();
    }

    public EmailAlreadyExistCException(String message) {
        super(message);
    }

    public EmailAlreadyExistCException(String message, Throwable cause) {
        super(message, cause);
    }
}
