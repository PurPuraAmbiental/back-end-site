package com.purpura.exception;

public abstract class PurpuraException extends RuntimeException {
    protected String userMessage;
    public PurpuraException(String message, String userMessage) {
        super(message);
        this.userMessage = userMessage;
    }

    public PurpuraException(String message) {
        super(message);
        this.userMessage = message;
    }

    public String getUserMessage() {
        return userMessage;
    }
}