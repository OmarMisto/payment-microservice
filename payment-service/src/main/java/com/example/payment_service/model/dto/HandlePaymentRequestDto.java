package com.example.payment_service.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    private ArrayList<Products> products;
}
