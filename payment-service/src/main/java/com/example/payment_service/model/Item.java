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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "item_gen")
    @SequenceGenerator(sequenceName = "item_gen",name = "item_sec" )
    private long itemId;
    @Column(unique = true,nullable = false)
    private long productId;
    private BigDecimal unitPrice;
    private Double discountPercentage;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "duesId")
    private Dues dues;
}
