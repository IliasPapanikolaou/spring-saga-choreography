package com.unipi.ipap.orderservice.service;

import com.unipi.ipap.commondtos.dto.OrderRequestDto;
import com.unipi.ipap.commondtos.event.OrderEvent;
import com.unipi.ipap.commondtos.event.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class OrderStatusPublisher {

    private final Sinks.Many<OrderEvent> orderSinks;

    // Create Publisher
    public void publishOrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
        OrderEvent orderEvent = new OrderEvent(orderRequestDto, orderStatus);
        orderSinks.tryEmitNext(orderEvent);
    }
}
