package com.example.payment_service.model.dto;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Item {
    private long productId;
    private BigDecimal unitPrice;
    private long discountPercentage;
    private int quantity;
}
