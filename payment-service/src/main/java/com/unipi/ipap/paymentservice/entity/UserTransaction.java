package com.unipi.ipap.paymentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_TRANSACTION_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransaction {
    @Id
    private Integer orderId;
    private Integer userId;
    private Double amount;
}
