package com.unipi.ipap.paymentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_BALANCE_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBalance {

    @Id
    private Integer userId;
    private Double amount;

}
