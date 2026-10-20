package com.example.payment_service.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class HandlePaymentRequestDto {
    private long userId;
    private final LocalDateTime requestDate = LocalDateTime.now();
    private String cardNo;
    private LocalDateTime expireDate;
    private List<Item> products;
}
