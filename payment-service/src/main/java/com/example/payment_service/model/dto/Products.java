package com.example.payment_service.model.dto;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Products {
    private long productId;
    private BigDecimal unitPrice;
    private int quantity;
}
