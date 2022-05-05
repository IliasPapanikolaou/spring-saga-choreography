package com.unipi.ipap.paymentservice.repository;

import com.unipi.ipap.paymentservice.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Integer> {
}
