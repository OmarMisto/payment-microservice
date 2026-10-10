package com.example.payment_service.model.dto;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ErrorMessageResponseDto {
    private String message;
    private int status;
    private String error;
    private final LocalDateTime date=LocalDateTime.now();
}
