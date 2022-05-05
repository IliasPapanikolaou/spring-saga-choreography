package com.unipi.ipap.commondtos.dto;

import com.unipi.ipap.commondtos.event.OrderStatus;

public record OrderResponseDto(
        Integer userId,
        Integer productId,
        Double amount,
        Integer orderId,
        OrderStatus orderStatus
) {
}
