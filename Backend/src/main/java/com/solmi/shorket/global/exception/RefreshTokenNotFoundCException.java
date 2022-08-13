package com.solmi.shorket.global.exception;

public class RefreshTokenNotFoundCException extends RuntimeException {

    public RefreshTokenNotFoundCException() {
        super();
    }

    public RefreshTokenNotFoundCException(String message) {
        super(message);
    }

    public RefreshTokenNotFoundCException(String message, Throwable cause) {
        super(message, cause);
    }
}
