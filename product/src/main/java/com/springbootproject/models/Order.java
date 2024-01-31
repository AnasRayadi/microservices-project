package com.springbootproject.models;

import lombok.Data;

@Data
public class Order {
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Double price;
    private String address;
}
