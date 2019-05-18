package com.parking.exception;

public class BOException extends RuntimeException {

    private static final long serialVersionUID = -2105906573622692634L;

    public BOException(String message) {
        super(message);
    }

    public BOException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
