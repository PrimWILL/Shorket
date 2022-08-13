package com.solmi.shorket.global.exception;

public class MarketNotFoundException extends RuntimeException {
    public MarketNotFoundException() {
    }

    public MarketNotFoundException(String message) {
        super(message);
    }

    public MarketNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
