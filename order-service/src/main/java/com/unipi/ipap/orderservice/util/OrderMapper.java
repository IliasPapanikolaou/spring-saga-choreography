package com.unipi.ipap.orderservice.util;

import com.unipi.ipap.commondtos.dto.OrderRequestDto;
import com.unipi.ipap.commondtos.event.OrderStatus;
import com.unipi.ipap.orderservice.entity.PurchaseOrder;

public class OrderMapper {

    public static PurchaseOrder toEntity(OrderRequestDto orderRequestDto) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(orderRequestDto.getProductId());
        purchaseOrder.setUserId(orderRequestDto.getUserId());
        purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(orderRequestDto.getAmount());
        return purchaseOrder;
    }

    public static OrderRequestDto toDto(PurchaseOrder purchaseOrder) {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUserId(purchaseOrder.getUserId());
        orderRequestDto.setProductId(purchaseOrder.getProductId());
        orderRequestDto.setAmount(purchaseOrder.getPrice());
        orderRequestDto.setOrderId(purchaseOrder.getId());
        return orderRequestDto;
    }

}
