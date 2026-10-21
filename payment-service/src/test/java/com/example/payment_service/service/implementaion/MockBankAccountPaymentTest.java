package com.example.payment_service.service.implementaion;

import com.example.payment_service.model.Dues;
import com.example.payment_service.model.Item;
import com.example.payment_service.model.Payment;
import com.example.payment_service.model.PaymentStatus;
import com.example.payment_service.model.dto.HandlePaymentRequestDto;
import com.example.payment_service.model.dto.HandlePaymentResponseDto;
import com.example.payment_service.model.dto.ItemDto;
import com.example.payment_service.repository.DuesRepository;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.service.exception.DuesNotFoundException;
import com.example.payment_service.service.exception.EmptyProductsListException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class MockBankAccountPaymentTest {
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private DuesRepository duesRepository;
    @InjectMocks
    private MockBankAccountPayment mockBankAccountPayment;
    @Test
    public void teatsTheSuccessOfHandlePayment(){
        List<ItemDto> items=new ArrayList<>();
        items.add(ItemDto.builder().productId(8701).unitPrice(BigDecimal.valueOf(49.99)).discountPercentage(0.10).quantity(2).build());
        items.add(ItemDto.builder().productId(8709).unitPrice(BigDecimal.valueOf(9.99)).discountPercentage(0.0).quantity(1).build());
        items.add(ItemDto.builder().productId(6033).unitPrice(BigDecimal.valueOf(100.0)).discountPercentage(0.10).quantity(3).build());
        HandlePaymentRequestDto mockHandlePaymentRequest= HandlePaymentRequestDto
                .builder()
                .userId(1001).orderId(11309).cardNo("CN-7701-6810")
                .expireDate(LocalDateTime.now())
                .build();
        List<Item> itemList=items.stream().map(item -> Item.builder()
                .itemId(item.getProductId()+1)
                .productId(item.getProductId())
                .unitPrice(item.getUnitPrice())
                .discountPercentage(item.getDiscountPercentage())
                .quantity(item.getQuantity())
                .build()).toList();
        Dues dues=Dues.builder()
                .duesId(80)
                .userId(1001)
                .orderId(11309)
                .items(itemList)
                .build();
        Mockito.when(duesRepository.findByUserIdAndOrderId(Mockito.anyLong(),Mockito.anyLong())).thenReturn(Optional.of(dues));
        Mockito.when(paymentRepository.save(Mockito.any(Payment.class))).thenReturn(Payment.builder()
                .paymentId(101)
                .paymentRequestNo(10002)
                .statusPayment(PaymentStatus.SUCCESS)
                .amount(BigDecimal.valueOf(409.97))
                .userId(1001)
                .dues(dues)
                .build());

        HandlePaymentResponseDto handlePaymentResponseDto=mockBankAccountPayment.handlePayment(mockHandlePaymentRequest);
        assertEquals(BigDecimal.valueOf(369.972),handlePaymentResponseDto.getAmount());
        assertEquals(101, handlePaymentResponseDto.getPaymentId());
    }
    @Test
    public void testTheFailOfHandlePaymentWithEmptyProductsList(){
//        List<ItemDto> products=new ArrayList<>();
//        HandlePaymentRequestDto mockHandlePaymentRequest= HandlePaymentRequestDto
//                .builder()
//                .userId(1001).cardNo("CN-7701-6810")
//                .expireDate(LocalDateTime.now())
//                .products(products)
//                .build();
//        assertThrows(EmptyProductsListException.class,()-> mockBankAccountPayment.handlePayment(mockHandlePaymentRequest),"empty product list");
    }

}