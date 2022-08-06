package com.solmi.shorket.global.exception;

public class RefreshTokenExpiredCException extends RuntimeException {

    public RefreshTokenExpiredCException() {
        super();
    }

    public RefreshTokenExpiredCException(String message) {
        super(message);
    }

    public RefreshTokenExpiredCException(String message, Throwable cause) {
        super(message, cause);
    }
}
