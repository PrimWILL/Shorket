package com.solmi.shorket.global.exception;

public class BoothNotFoundException extends RuntimeException{

    public BoothNotFoundException() {
        super();
    }

    public BoothNotFoundException(String message) {
        super(message);
    }

    public BoothNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
