package com.example.payment_service.service.implementaion;

import com.example.payment_service.model.Dues;
import com.example.payment_service.model.Payment;
import com.example.payment_service.model.PaymentStatus;
import com.example.payment_service.model.dto.HandlePaymentRequestDto;
import com.example.payment_service.model.dto.HandlePaymentResponseDto;
import com.example.payment_service.model.dto.Item;
import com.example.payment_service.repository.DuesRepository;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.service.exception.DuesNotFoundException;
import com.example.payment_service.service.exception.InvalidAmountException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class MockBankAccountPayment implements PaymentMethod{
    private final PaymentRepository paymentRepository;
    private final DuesRepository duesRepository;
    @Override
    @Transactional
    public HandlePaymentResponseDto handlePayment(HandlePaymentRequestDto handlePaymentRequestDto) {
        Dues dues=duesRepository.findByUserIdAndOrderId(handlePaymentRequestDto.getUserId(),handlePaymentRequestDto.getOrderId())
                .orElseThrow(()->new DuesNotFoundException("dues not found for the requested user and order"));
        BigDecimal amount= dues.getItems().stream()
                .map(item -> (item.getUnitPrice().multiply(BigDecimal.valueOf( item.getDiscountPercentage()))).multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal::add)
                .orElseThrow(()->new InvalidAmountException("invalid amount exception"));
         Payment savedPayment= paymentRepository.save(Payment.builder()
                 .statusPayment(PaymentStatus.SUCCESS)
                 .amount(amount)
                 .dues(dues)
                 .userId(handlePaymentRequestDto.getUserId())
                 .build());
         return HandlePaymentResponseDto.builder()
                 .paymentId(savedPayment.getPaymentId())
                 .paymentStatus(savedPayment.getStatusPayment().name())
                 .createdAt(savedPayment.getCreatedAt())
                 .products(dues.getItems().stream().map(item -> Item.builder().productId(item.getProductId()).unitPrice(item.getUnitPrice()).discountPercentage(item.getDiscountPercentage()).quantity(item.getQuantity()).build()).toList())
                 .amount(amount)
                 .build();
    }
}
