package com.example.payment_service.controller;

import com.example.payment_service.model.dto.HandlePaymentRequestDto;
import com.example.payment_service.model.dto.HandlePaymentResponseDto;
import com.example.payment_service.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment/")
@RequiredArgsConstructor
public class PaymentController {
    private final IPaymentService iPaymentService;
    @PostMapping("handle")
    public ResponseEntity<HandlePaymentResponseDto> handlePaymentController(@RequestBody HandlePaymentRequestDto handlePaymentRequestDto){
        return ResponseEntity.ok().body(iPaymentService.handlePaymentService(handlePaymentRequestDto));
    }
}
