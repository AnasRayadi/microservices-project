package com.orderservice.order.controller;

import com.orderservice.order.dto.AddOrderRequest;
import com.orderservice.order.model.Order;
import com.orderservice.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    @GetMapping
    public List<Order> getOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("{orderId}")
    public Order getOrder(@PathVariable Integer orderId){
        return orderService.getOrder(orderId);
    }
    @PostMapping
    public void addOrder(@RequestBody AddOrderRequest request){
        orderService.addOrder(request);
    }
}
