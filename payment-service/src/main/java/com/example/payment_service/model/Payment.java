package com.example.payment_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "payment_sec")
    @SequenceGenerator(name = "payment_sec",sequenceName = "payment_sec")
    private long paymentId;
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "payment_request_sec")
    @SequenceGenerator(name = "payment_request_sec",sequenceName = "payment_request_sec")
    @Column(nullable = false,unique = true)
    private long paymentRequestNo;
    @Enumerated(EnumType.STRING)
    private PaymentStatus statusPayment;
    private BigDecimal amount;
    @OneToOne
    @JoinColumn(name = "duesId")
    private Dues dues;
    @Column(unique = true,nullable = false)
    private long userId;
    private final LocalDateTime createdAt=LocalDateTime.now();

}
