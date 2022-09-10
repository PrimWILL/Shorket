package com.solmi.shorket.global.exception;

public class DuplicateBoothInterestException extends RuntimeException {

    public DuplicateBoothInterestException() {
        super();
    }

    public DuplicateBoothInterestException(String message) {
        super(message);
    }

    public DuplicateBoothInterestException(String message, Throwable cause) {
        super(message, cause);
    }
}
