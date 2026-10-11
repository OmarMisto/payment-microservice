package com.example.payment_service.service.implementaion;

import com.example.payment_service.model.PaidProduct;
import com.example.payment_service.model.Payment;
import com.example.payment_service.model.PaymentStatus;
import com.example.payment_service.model.dto.HandlePaymentRequestDto;
import com.example.payment_service.model.dto.HandlePaymentResponseDto;
import com.example.payment_service.model.dto.Products;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.service.exception.EmptyProductsListException;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class MockBankAccountPaymentTest {
    @Mock
    private PaymentRepository paymentRepository;
    @InjectMocks
    private MockBankAccountPayment mockBankAccountPayment;
    @Test
    public void teatsTheSuccessOfHandlePayment(){
        List<Products> products=new ArrayList<>();
        products.add(Products.builder().productId(8701).unitPrice(BigDecimal.valueOf(49.99)).quantity(2).build());
        products.add(Products.builder().productId(8709).unitPrice(BigDecimal.valueOf(9.99)).quantity(1).build());
        products.add(Products.builder().productId(6033).unitPrice(BigDecimal.valueOf(100.0)).quantity(3).build());
        HandlePaymentRequestDto mockHandlePaymentRequest= HandlePaymentRequestDto
                .builder()
                .userId(1001).cardNo("CN-7701-6810")
                .expireDate(LocalDateTime.now())
                .products(products)
                .build();
        List<PaidProduct> paidProducts=new ArrayList<>();
        paidProducts.add(PaidProduct.builder().paidProductId(1).productId(8701).unitPrice(BigDecimal.valueOf(49.99)).quantity(2).build());
        paidProducts.add(PaidProduct.builder().paidProductId(2).productId(8709).unitPrice(BigDecimal.valueOf(9.99)).quantity(1).build());
        paidProducts.add(PaidProduct.builder().paidProductId(3).productId(6033).unitPrice(BigDecimal.valueOf(100.0)).quantity(3).build());
        Mockito.when(paymentRepository.save(Mockito.any(Payment.class))).thenReturn(Payment.builder()
                .paymentId(101)
                .paymentRequestNo(10002)
                .statusPayment(PaymentStatus.COMPLETED)
                .amount(BigDecimal.valueOf(409.97))
                .paidProducts(paidProducts)
                .userId(1001)
                .build());
        HandlePaymentResponseDto handlePaymentResponseDto=mockBankAccountPayment.handlePayment(mockHandlePaymentRequest);
        assertEquals(BigDecimal.valueOf(409.97),handlePaymentResponseDto.getAmount());
        assertEquals(101, handlePaymentResponseDto.getPaymentId());
    }
    @Test
    public void testTheFailOfHandlePaymentWithEmptyProductsList(){
        List<Products> products=new ArrayList<>();
        HandlePaymentRequestDto mockHandlePaymentRequest= HandlePaymentRequestDto
                .builder()
                .userId(1001).cardNo("CN-7701-6810")
                .expireDate(LocalDateTime.now())
                .products(products)
                .build();
        assertThrows(EmptyProductsListException.class,()-> mockBankAccountPayment.handlePayment(mockHandlePaymentRequest),"empty product list");
    }

}