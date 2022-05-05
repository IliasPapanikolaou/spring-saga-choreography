package com.unipi.ipap.commondtos.event;

import com.unipi.ipap.commondtos.dto.PaymentRequestDto;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class PaymentEvent implements Event {

    private final UUID eventId;
    private final Date eventDate;
    private PaymentRequestDto paymentRequestDto;
    private PaymentStatus paymentStatus;

    public PaymentEvent(PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus) {
        this.eventId = UUID.randomUUID();
        this.eventDate = Date.from(Instant.now());
        this.paymentRequestDto = paymentRequestDto;
        this.paymentStatus = paymentStatus;
    }

    public PaymentEvent() {
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

    public PaymentRequestDto getPaymentRequestDto() {
        return paymentRequestDto;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
