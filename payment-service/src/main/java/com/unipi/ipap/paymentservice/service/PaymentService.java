package com.unipi.ipap.paymentservice.service;

import com.unipi.ipap.commondtos.dto.OrderRequestDto;
import com.unipi.ipap.commondtos.dto.PaymentRequestDto;
import com.unipi.ipap.commondtos.event.OrderEvent;
import com.unipi.ipap.commondtos.event.PaymentEvent;
import com.unipi.ipap.commondtos.event.PaymentStatus;
import com.unipi.ipap.paymentservice.entity.UserTransaction;
import com.unipi.ipap.paymentservice.repository.UserBalanceRepository;
import com.unipi.ipap.paymentservice.repository.UserTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserBalanceRepository userBalanceRepository;
    private final UserTransactionRepository userTransactionRepository;

    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        // Get the user id, order id and amount from OrderRequestDto
        OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(
                orderRequestDto.getOrderId(),
                orderRequestDto.getUserId(),
                orderRequestDto.getAmount()
        );

        // Check the balance sufficiency
        return userBalanceRepository.findById(orderRequestDto.getUserId())
                // check if user balance >= order amount
                .filter(userBalance -> userBalance.getAmount() >= orderRequestDto.getAmount())
                // if balance is sufficient, deduct the order amount from balance
                .map(userBalance -> {
                    userBalance.setAmount(userBalance.getAmount() - orderRequestDto.getAmount());
                    userTransactionRepository.save(
                            new UserTransaction(
                                    orderRequestDto.getOrderId(),
                                    orderRequestDto.getUserId(),
                                    orderRequestDto.getAmount())
                    );
                    return new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_COMPLETED);
                })
                .orElse(new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_FAILED));
    }

    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {
        // Return the deducted amount to user
        userTransactionRepository.findById(orderEvent.getOrderRequestDto().getOrderId())
                .ifPresent(userTransaction -> {
                    userTransactionRepository.delete(userTransaction);
                    userBalanceRepository.findById(userTransaction.getUserId())
                            .ifPresent(userBalance -> userBalance
                                    .setAmount(userBalance.getAmount() + userTransaction.getAmount()));
                });
    }
}
