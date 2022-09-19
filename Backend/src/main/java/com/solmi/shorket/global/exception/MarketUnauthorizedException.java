package com.solmi.shorket.global.exception;

public class MarketUnauthorizedException extends RuntimeException {
    public MarketUnauthorizedException() {
        super();
    }

    public MarketUnauthorizedException(String message) {
        super(message);
    }
}
