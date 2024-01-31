package com.orderservice.order.rabbitmq;

import com.orderservice.order.config.RabbitMQConfig;
import com.orderservice.order.model.Order;
import com.orderservice.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderPublisher {
    private final RabbitTemplate rabbitTemplate ;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPublisher.class);

    public void publishOrderCreatedMessage(Order order) {
        LOGGER.info(String.format("Sending message: %s", order.toString()));
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_DIRECT_EXCHANGE, RabbitMQConfig.ORDER_CREATED_ROUTING_KEY, order );
    }
}
