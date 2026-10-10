package com.example.payment_service.model.dto;

import com.example.payment_service.model.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class HandlePaymentResponseDto {
    private long paymentId;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
    private List<Products> products;
    private BigDecimal amount;
}
