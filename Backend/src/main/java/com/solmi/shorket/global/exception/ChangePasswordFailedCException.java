package com.solmi.shorket.global.exception;

public class ChangePasswordFailedCException extends RuntimeException {

    public ChangePasswordFailedCException() {
        super();
    }

    public ChangePasswordFailedCException(String message) {
        super(message);
    }

    public ChangePasswordFailedCException(String message, Throwable cause) {
        super(message, cause);
    }
}
