package com.example.payment_service.service.implementaion;

import com.example.payment_service.model.Dues;
import com.example.payment_service.model.dto.HandlePaymentEvent;
import com.example.payment_service.model.dto.HandlePaymentRequestDto;
import com.example.payment_service.model.dto.HandlePaymentResponseDto;
import com.example.payment_service.repository.DuesRepository;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.service.IPaymentService;
import com.example.payment_service.service.exception.PaymentMethodNotFoundException;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final PaymentMethod paymentMethod;
    private final KafkaPaymentProducer kafkaPaymentProducer;

    @Override
    public HandlePaymentResponseDto handlePaymentService(HandlePaymentRequestDto handlePaymentRequestDto) {
        HandlePaymentResponseDto handlePaymentResponseDto= paymentMethod.handlePayment(handlePaymentRequestDto);
        kafkaPaymentProducer.sendHandlePaymentEvent(HandlePaymentEvent.builder()
                .orderId(handlePaymentResponseDto.getOrderId())
                .paymentStatus(handlePaymentResponseDto.getPaymentStatus())
                .event("payment-event")
                .date(LocalDateTime.now())
                .build());
        return handlePaymentResponseDto;
    }
}
