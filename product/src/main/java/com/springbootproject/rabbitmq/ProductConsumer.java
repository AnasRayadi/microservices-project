package com.springbootproject.rabbitmq;

import com.springbootproject.dao.ProductDao;
//import com.springbootproject.models.Order;
import com.springbootproject.models.Product;
import com.springbootproject.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.common.models.Order;

@Service
@RequiredArgsConstructor
public class ProductConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConsumer.class);
    private final ProductService productService;
    private final ProductDao productDao;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeMessage(Order order) {
        LOGGER.info(String.format("Message received: %s", order.toString()));
        Integer id = order.getProductId();
        Product product = productService.getProduct(id);
        product.setQuantity(product.getQuantity() - order.getQuantity());
        productDao.updateProduct(product);
    }

}
