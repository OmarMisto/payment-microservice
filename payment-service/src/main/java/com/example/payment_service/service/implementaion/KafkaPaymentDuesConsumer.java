package com.example.payment_service.service.implementaion;

import com.example.payment_service.model.Dues;
import com.example.payment_service.model.Item;
import com.example.payment_service.model.dto.PaymentDuesEvent;
import com.example.payment_service.service.IDuesService;
import com.example.payment_service.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaPaymentDuesConsumer {
    private final IDuesService iDuesService;
    @KafkaListener(topics = "payment-dues",groupId = "order-service")
    public void paymentDuesListener(PaymentDuesEvent paymentDuesEvent){
        iDuesService.saveDues(Dues.builder()
                .userId(paymentDuesEvent.getUserId())
                .orderId(paymentDuesEvent.getOrderId())
                .items(paymentDuesEvent.getItems().stream().map(item -> Item.builder().productId(item.getProductId()).unitPrice(item.getUnitPrice()).discountPercentage(item.getDiscountPercentage()).quantity(item.getQuantity()).build()).toList())
                .build());
    }
}
