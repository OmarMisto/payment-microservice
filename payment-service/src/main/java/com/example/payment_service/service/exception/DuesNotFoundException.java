package com.example.payment_service.service.exception;

public class DuesNotFoundException extends RuntimeException{
    public DuesNotFoundException(String s) {
        super(s);
    }
}
