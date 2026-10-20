package com.example.payment_service.model.dto;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class HandlePaymentEvent {
    private long orderId;
    private String paymentStatus;
    private String event;
    private LocalDateTime date;
}
