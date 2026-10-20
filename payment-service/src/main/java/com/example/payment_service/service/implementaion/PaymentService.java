package com.example.payment_service.service.implementaion;

import com.example.payment_service.model.Dues;
import com.example.payment_service.model.dto.HandlePaymentRequestDto;
import com.example.payment_service.model.dto.HandlePaymentResponseDto;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.service.IPaymentService;
import com.example.payment_service.service.exception.PaymentMethodNotFoundException;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final PaymentRepository paymentRepository;
    private PaymentMethod paymentMethodFactory(String paymentMethod){
        if (paymentMethod!=null && paymentMethod.equalsIgnoreCase("mock-payment")){
            return new MockBankAccountPayment(paymentRepository);
        }
        else
            throw new PaymentMethodNotFoundException("Payment method not found");
    }
    @Override
    public HandlePaymentResponseDto handlePaymentService(HandlePaymentRequestDto handlePaymentRequestDto,String paymentMethod) {
        PaymentMethod paymentMethods = paymentMethodFactory(paymentMethod);
        return paymentMethods.handlePayment(handlePaymentRequestDto);
    }
}
