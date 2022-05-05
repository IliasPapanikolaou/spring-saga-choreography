package com.unipi.ipap.orderservice.config;

import com.unipi.ipap.commondtos.event.OrderStatus;
import com.unipi.ipap.commondtos.event.PaymentStatus;
import com.unipi.ipap.orderservice.entity.PurchaseOrder;
import com.unipi.ipap.orderservice.repository.OrderRepository;
import com.unipi.ipap.orderservice.service.OrderStatusPublisher;
import com.unipi.ipap.orderservice.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class OrderStatusUpdateHandler {

    private final OrderRepository orderRepository;
    private final OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public void updateOrder(Integer  id, Consumer<PurchaseOrder> consumer) {
        orderRepository.findById(id)
                .ifPresent(consumer.andThen(this::updateOrder));
    }

    private void updateOrder(PurchaseOrder purchaseOrder) {
        boolean isPaymentComplete = PaymentStatus.PAYMENT_COMPLETED.equals(purchaseOrder.getPaymentStatus());
        OrderStatus orderStatus = isPaymentComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);
        if(!isPaymentComplete) {
            orderStatusPublisher.publishOrderEvent(OrderMapper.toDto(purchaseOrder), orderStatus);
        }
    }
}
