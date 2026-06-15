package com.myfinbank.loan.exception;

public class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException(String message) {
        super(message);
    }
}
