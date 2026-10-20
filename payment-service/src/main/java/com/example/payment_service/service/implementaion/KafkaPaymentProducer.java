package com.example.payment_service.service.implementaion;

import com.example.payment_service.model.dto.HandlePaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPaymentProducer {
    private final String topic="handle-payment";
    @Autowired
    private KafkaTemplate<String, HandlePaymentEvent>kafkaTemplate;
    public void sendHandlePaymentEvent(HandlePaymentEvent handlePaymentEvent){
        kafkaTemplate.send(topic,""+handlePaymentEvent.getOrderId(),handlePaymentEvent);
    }

}
