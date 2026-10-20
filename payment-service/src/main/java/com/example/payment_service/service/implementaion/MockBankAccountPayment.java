package com.example.payment_service.service.implementaion;

import com.example.payment_service.model.Payment;
import com.example.payment_service.model.PaymentStatus;
import com.example.payment_service.model.dto.HandlePaymentRequestDto;
import com.example.payment_service.model.dto.HandlePaymentResponseDto;
import com.example.payment_service.model.dto.Item;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.service.exception.EmptyProductsListException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class MockBankAccountPayment implements PaymentMethod{
    private final PaymentRepository paymentRepository;
    @Override
    @Transactional
    public HandlePaymentResponseDto handlePayment(HandlePaymentRequestDto handlePaymentRequestDto) {
       List<com.example.payment_service.model.Item> paidProducts=handlePaymentRequestDto.getProducts().stream()
               .map(products -> com.example.payment_service.model.Item
                        .builder()
                        .productId(products.getProductId())
                        .unitPrice(products.getUnitPrice())
                        .quantity(products.getQuantity())
                        .build())
               .toList();
         if(paidProducts.isEmpty()){
             throw new EmptyProductsListException("empty product list");
         }
         BigDecimal totalAmount = paidProducts
                  .stream()
                  .map(paidProduct -> paidProduct.getUnitPrice().multiply(BigDecimal.valueOf( paidProduct.getQuantity())))
                  .reduce(BigDecimal.ZERO, BigDecimal::add);
         Payment payment= paymentRepository.save(Payment.builder()
                 .statusPayment(PaymentStatus.COMPLETED)
                 .amount(totalAmount)
                 .paidProducts(paidProducts)
                 .userId(handlePaymentRequestDto.getUserId())
                 .build()
         );
         return HandlePaymentResponseDto.builder()
                 .paymentId(payment.getPaymentId())
                 .paymentStatus(payment.getStatusPayment().name())
                 .createdAt(payment.getCreatedAt())
                 .products(payment.getPaidProducts().stream()
                         .map(paidProduct -> Item.builder().productId(paidProduct.getProductId()).unitPrice(paidProduct.getUnitPrice()).quantity(paidProduct.getQuantity()).build())
                         .toList())
                 .amount(payment.getAmount())
                 .build();
    }
}
