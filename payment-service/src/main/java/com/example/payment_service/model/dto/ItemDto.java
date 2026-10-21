package com.example.payment_service.model.dto;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ItemDto {
    private long productId;
    private BigDecimal unitPrice;
    private Double discountPercentage;
    private int quantity;
}
