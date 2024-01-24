package org.example;

public class StoregeIsFullException extends RuntimeException {
    public StoregeIsFullException() {
    }

    public StoregeIsFullException(String message) {
        super(message);
    }

    public StoregeIsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoregeIsFullException(Throwable cause) {
        super(cause);
    }

    public StoregeIsFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
