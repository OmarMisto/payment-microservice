package com.example.payment_service.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class HandlePaymentResponseDto {
    private long paymentId;
    private long orderId;
    private String paymentStatus;
    private LocalDateTime createdAt;
    private List<Item> products;
    private BigDecimal amount;
}
