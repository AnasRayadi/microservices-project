package com.orderservice.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderRequest {
    private Integer productId;
    private Integer quantity;
    private Double price;
    private String address;
}
