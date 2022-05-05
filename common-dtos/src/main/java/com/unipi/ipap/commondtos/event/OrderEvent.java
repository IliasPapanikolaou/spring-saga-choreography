package com.unipi.ipap.commondtos.event;

import com.unipi.ipap.commondtos.dto.OrderRequestDto;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class OrderEvent implements Event {

    private final UUID eventId;
    private final Date eventDate;
    private OrderRequestDto orderRequestDto;
    private OrderStatus orderStatus;

    public OrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
        this.eventId = UUID.randomUUID();
        this.eventDate = Date.from(Instant.now());
        this.orderRequestDto = orderRequestDto;
        this.orderStatus = orderStatus;
    }

    public OrderEvent() {
        this.eventId = UUID.randomUUID();
        this.eventDate = Date.from(Instant.now());
    }

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.eventDate;
    }

    public OrderRequestDto getOrderRequestDto() {
        return orderRequestDto;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
