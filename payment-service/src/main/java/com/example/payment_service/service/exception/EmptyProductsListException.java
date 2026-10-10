package com.example.payment_service.service.exception;

public class EmptyProductsListException extends RuntimeException{
    public EmptyProductsListException(String message){
        super(message);
    }
}
