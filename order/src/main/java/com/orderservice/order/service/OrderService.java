package com.orderservice.order.service;

import com.orderservice.order.config.RabbitMQConfig;
import com.orderservice.order.dto.AddOrderRequest;
import com.orderservice.order.feign.OrderInterface;
import com.orderservice.order.model.Order;
import com.orderservice.order.rabbitmq.OrderPublisher;
import com.orderservice.order.repo.OrderRepo;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final RabbitTemplate rabbitTemplate;
    private final OrderPublisher orderPublisher;

    /*@Autowired
    OrderInterface orderInterface;*/
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
    public Order getOrder(Integer id) {
        return orderRepo.findById(id).orElseThrow(()-> new RuntimeException("Order with id [%s] not found".formatted(id)));
    }
    public void addOrder(AddOrderRequest request) {
        if (request.getProductId() != null && request.getQuantity() != null && request.getPrice() != null && request.getAddress() != null) {
            /*Integer quantity = orderInterface.getQuantity(request.getProductId());
            if (quantity < request.getQuantity()) {
                throw new RuntimeException("Not enough quantity");
            }*/

            Order order = Order.builder()
                    .productId(request.getProductId())
                    .quantity(request.getQuantity())
                    .price(request.getPrice())
                    .address(request.getAddress())
                    .build();
            orderRepo.save(order);

            orderPublisher.publishOrderCreatedMessage(order);
        }
        else{
            throw new RuntimeException("Invalid request");
        }
    }
}
