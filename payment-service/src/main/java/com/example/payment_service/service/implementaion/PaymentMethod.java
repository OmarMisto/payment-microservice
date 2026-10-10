package com.example.payment_service.service.implementaion;

import com.example.payment_service.model.dto.HandlePaymentRequestDto;
import com.example.payment_service.model.dto.HandlePaymentResponseDto;

public interface PaymentMethod {
    public HandlePaymentResponseDto handlePayment(HandlePaymentRequestDto handlePaymentRequestDto);
}
