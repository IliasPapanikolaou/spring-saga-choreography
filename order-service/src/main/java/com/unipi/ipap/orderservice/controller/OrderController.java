package com.unipi.ipap.orderservice.controller;

import com.unipi.ipap.commondtos.dto.OrderRequestDto;
import com.unipi.ipap.orderservice.entity.PurchaseOrder;
import com.unipi.ipap.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<PurchaseOrder> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return new ResponseEntity<>(orderService.createOrder(orderRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }
}
