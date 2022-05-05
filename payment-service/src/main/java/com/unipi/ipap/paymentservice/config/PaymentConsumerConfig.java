package com.unipi.ipap.paymentservice.config;

import com.unipi.ipap.commondtos.event.OrderEvent;
import com.unipi.ipap.commondtos.event.OrderStatus;
import com.unipi.ipap.commondtos.event.PaymentEvent;
import com.unipi.ipap.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class PaymentConsumerConfig {

    private final PaymentService paymentService;

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    /*
     * Algorithm:
     * ----------
     * - Get the user id, order id and amount from OrderRequestDto
     * - Check the balance sufficiency
     * - If balance is sufficient -> Payment completed and deduct amount from DB
     * - If balance is insufficient -> Cancel order and update the amount in DB
     */
    public Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {

        if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())) {
            return Mono.fromSupplier(() -> paymentService.newOrderEvent(orderEvent));
        } else {
            return Mono.fromRunnable(() -> paymentService.cancelOrderEvent(orderEvent));
        }
    }
}
