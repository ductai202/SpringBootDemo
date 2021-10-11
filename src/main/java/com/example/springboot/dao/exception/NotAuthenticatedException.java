package com.example.springboot.dao.exception;

public class NotAuthenticatedException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;
    public NotAuthenticatedException() {
        super();
    }

    public NotAuthenticatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotAuthenticatedException(final String message) {
        super(message);
    }

    public NotAuthenticatedException(final Throwable cause) {
        super(cause);
    }
}
