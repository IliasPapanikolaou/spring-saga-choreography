package com.unipi.ipap.paymentservice;

import com.unipi.ipap.paymentservice.entity.UserBalance;
import com.unipi.ipap.paymentservice.repository.UserBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RequiredArgsConstructor
public class PaymentServiceApplication {

    private final UserBalanceRepository userBalanceRepository;

    public static void main(String[] args) {
        SpringApplication.run((PaymentServiceApplication.class));
    }

    // Init UserBalance
    @PostConstruct
    private void initUserBalanceInDB() {
        userBalanceRepository.saveAll(
                Stream.of(
                        new UserBalance(101, 500.0),
                        new UserBalance(102, 1200.0),
                        new UserBalance(103, 5000.0),
                        new UserBalance(104, 800.0),
                        new UserBalance(105, 1300.0)
                ).collect(Collectors.toList())
        );
    }
}
