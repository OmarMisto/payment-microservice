package com.example.payment_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "paid_product_sec")
    @SequenceGenerator(sequenceName = "paid_product_sec",name = "paid_product_sec" )
    private long paidProductId;
    @Column(unique = true,nullable = false)
    private long productId;
    private BigDecimal unitPrice;
    private long discountPercentage;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "duesId")
    private Dues dues;
}
