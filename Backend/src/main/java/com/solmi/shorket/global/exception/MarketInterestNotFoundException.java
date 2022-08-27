package com.solmi.shorket.global.exception;

public class MarketInterestNotFoundException extends RuntimeException {
    public MarketInterestNotFoundException() {
    }

    public MarketInterestNotFoundException(String message) {
        super(message);
    }

    public MarketInterestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
