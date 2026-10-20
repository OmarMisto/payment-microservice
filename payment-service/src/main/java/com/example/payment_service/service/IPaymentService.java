package com.example.payment_service.service;

import com.example.payment_service.model.Dues;
import com.example.payment_service.model.dto.HandlePaymentRequestDto;
import com.example.payment_service.model.dto.HandlePaymentResponseDto;
import com.example.payment_service.service.implementaion.PaymentMethod;

public interface IPaymentService {
    public HandlePaymentResponseDto handlePaymentService(HandlePaymentRequestDto handlePaymentRequestDto);
}
