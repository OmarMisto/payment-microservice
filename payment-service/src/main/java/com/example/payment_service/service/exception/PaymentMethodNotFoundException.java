package com.example.payment_service.service.exception;

public class PaymentMethodNotFoundException extends RuntimeException{
    public PaymentMethodNotFoundException(String message){
        super(message);
    }
}
