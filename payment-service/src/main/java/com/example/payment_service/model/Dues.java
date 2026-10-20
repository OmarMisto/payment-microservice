package com.example.payment_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Dues {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "dues_gen")
    @SequenceGenerator( name = "dues_gen",sequenceName = "dues_sequence")
    private long duesId;
    private long userId;
    @Column(unique = true,nullable = false)
    private long orderId;
    @OneToMany(mappedBy = "dues",cascade = CascadeType.ALL)
    private List<Item> items;
}
