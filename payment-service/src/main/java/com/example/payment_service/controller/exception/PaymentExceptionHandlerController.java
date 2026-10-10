package com.example.payment_service.controller.exception;

import com.example.payment_service.model.dto.ErrorMessageResponseDto;
import com.example.payment_service.service.exception.EmptyProductsListException;
import com.example.payment_service.service.exception.PaymentMethodNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentExceptionHandlerController {
    @ExceptionHandler(PaymentMethodNotFoundException.class)
    public ResponseEntity<ErrorMessageResponseDto> paymentMethodNotFoundExceptionHandlerController(PaymentMethodNotFoundException paymentMethodNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessageResponseDto.builder()
                        .message(paymentMethodNotFoundException.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error("NOT_FOUND")
                        .build());
    }
    @ExceptionHandler(EmptyProductsListException.class)
    public ResponseEntity<ErrorMessageResponseDto> emptyProductsListExceptionHandlerController(EmptyProductsListException emptyProductsListException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessageResponseDto.builder()
                        .message(emptyProductsListException.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("BAD_REQUEST")
                        .build());
    }

}
