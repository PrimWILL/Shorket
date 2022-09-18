package com.solmi.shorket.global.exception;

public class BoothImgNotFoundException extends RuntimeException{

    public BoothImgNotFoundException() {
        super();
    }

    public BoothImgNotFoundException(String message) {
        super(message);
    }

    public BoothImgNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

