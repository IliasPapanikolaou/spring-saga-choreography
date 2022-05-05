package com.unipi.ipap.paymentservice.repository;

import com.unipi.ipap.paymentservice.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Integer> {
}
