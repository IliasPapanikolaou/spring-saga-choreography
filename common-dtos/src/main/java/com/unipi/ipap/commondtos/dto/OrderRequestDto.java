package com.unipi.ipap.commondtos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {

    private Integer userId;
    private Integer productId;
    private Double amount;
    private Integer orderId;

}