package com.unipi.ipap.commondtos.dto;

public record PaymentRequestDto(
        Integer orderId,
        Integer userId,
        Double amount
) {
}
