package com.unipi.ipap.orderservice.service;

import com.unipi.ipap.commondtos.dto.OrderRequestDto;
import com.unipi.ipap.commondtos.event.OrderStatus;
import com.unipi.ipap.orderservice.entity.PurchaseOrder;
import com.unipi.ipap.orderservice.repository.OrderRepository;
import com.unipi.ipap.orderservice.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {

        PurchaseOrder purchaseOrder = OrderMapper.toEntity(orderRequestDto);
        PurchaseOrder order = orderRepository.save(purchaseOrder);

        // Set orderId to Dto
        orderRequestDto.setOrderId(order.getId());

        // Produce Kafka event with status ORDER_CREATED
        orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);

        return order;
    }

    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }

}
