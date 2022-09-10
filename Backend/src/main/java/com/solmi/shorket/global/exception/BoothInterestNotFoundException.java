package com.solmi.shorket.global.exception;

public class BoothInterestNotFoundException extends RuntimeException{

    public BoothInterestNotFoundException() {
        super();
    }

    public BoothInterestNotFoundException(String message) {
        super(message);
    }

    public BoothInterestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}