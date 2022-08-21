package com.spring.rest;

public class IssueNotFoundException extends RuntimeException{

    public IssueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IssueNotFoundException(String message) {
        super(message);
    }

    public IssueNotFoundException(Throwable cause) {
        super(cause);
    }
}
