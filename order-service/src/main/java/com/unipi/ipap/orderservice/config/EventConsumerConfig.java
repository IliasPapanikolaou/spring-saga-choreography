package com.unipi.ipap.orderservice.config;

import com.unipi.ipap.commondtos.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class EventConsumerConfig {

    private final OrderStatusUpdateHandler orderStatusUpdateHandler;

    /*
    * Algorithm
    * ---------
    * - Listen to payment-event-topic
    * - Check payment status
    * - If payment status completed -> complete the order
    * - If payment status failed -> cancel the order
    */
    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer() {
        return paymentEvent -> orderStatusUpdateHandler
                .updateOrder(
                        paymentEvent.getPaymentRequestDto().orderId(),
                        purchaseOrder -> purchaseOrder.setPaymentStatus(paymentEvent.getPaymentStatus())
                );
    }
}
