package com.solmi.shorket.global.exception;

public class UserRoleNotFoundCException extends RuntimeException {

    public UserRoleNotFoundCException() {
        super();
    }

    public UserRoleNotFoundCException(String message) {
        super(message);
    }

    public UserRoleNotFoundCException(String message, Throwable cause) {
        super(message, cause);
    }
}
