package com.example.payment_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.math.BigDecimal;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class PaidProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "paid_product_sec")
    @SequenceGenerator(sequenceName = "paid_product_sec",name = "paid_product_sec" )
    private long paidProductId;
    private BigDecimal unitPrice;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payment payment;
}
