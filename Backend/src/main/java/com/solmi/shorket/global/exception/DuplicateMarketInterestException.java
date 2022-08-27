package com.solmi.shorket.global.exception;

public class DuplicateMarketInterestException extends RuntimeException {
    public DuplicateMarketInterestException() {
    }

    public DuplicateMarketInterestException(String message) {
        super(message);
    }

    public DuplicateMarketInterestException(String message, Throwable cause) {
        super(message, cause);
    }
}
