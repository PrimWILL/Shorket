package com.solmi.shorket.global.exception;

public class GetBoothFailedException extends RuntimeException{

    public GetBoothFailedException() {
        super();
    }

    public GetBoothFailedException(String message) {
        super(message);
    }

    public GetBoothFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
