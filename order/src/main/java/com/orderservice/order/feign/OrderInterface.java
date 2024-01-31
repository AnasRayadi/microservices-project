package com.orderservice.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("PRODUCT-SERVICE")
public interface OrderInterface {
    @GetMapping("api/v1/products/quantity/{productId}")
    public Integer getQuantity(@PathVariable Integer productId);
}
