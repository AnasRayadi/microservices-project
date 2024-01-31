package com.springbootproject.services;

import com.springbootproject.dao.ProductDao;
import com.springbootproject.exception.RequestValidationException;
import com.springbootproject.exception.ResourceNotFoundException;
import com.springbootproject.models.Product;
import com.springbootproject.repositories.ProductRepository;
import com.springbootproject.requests.ProductRegistrationRequest;
import com.springbootproject.requests.ProductUpdateRequest;
import com.orderservice.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    private final ProductRepository productRepository;

    //private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    public List<Product> getAllProducts(){
        return productDao.selectAllProducts();
    }
    public Product getProduct(Integer id){
        return productDao.selectProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id [%s] not found".formatted(id)));
    }
    public void addProduct(ProductRegistrationRequest productRegistrationRequest){
        var product = Product.builder()
                .title(productRegistrationRequest.title())
                .image(productRegistrationRequest.image())
                .description(productRegistrationRequest.description())
                .price(productRegistrationRequest.price())
                .quantity(productRegistrationRequest.quantity())
                .build();
        productDao.insertProduct(product);
    }
    public void removeProduct(Integer id){
        if(!productDao.existProduct(id)){
            throw new ResourceNotFoundException("Product with id [%s] doesn't exist".formatted(id));
        }
        productDao.deleteProduct(id);
    }

    public void updateProduct(Integer id, ProductUpdateRequest updateRequest){
            Product product = getProduct(id); //check if the product that you want to update exist or not
            boolean change = false;
            if(updateRequest.title()!= null && updateRequest.title() != product.getTitle()){
                product.setTitle(updateRequest.title());
                change = true;
            }
            if (updateRequest.image() != null && updateRequest.image() != product.getImage()){
                product.setImage(updateRequest.image());
                change = true;
            }
            if (updateRequest.description() != null && updateRequest.description() != product.getDescription()) {
                product.setDescription(updateRequest.description());
                change = true;
            }
            if (updateRequest.price() != null && updateRequest.price() != product.getPrice()) {
                product.setPrice(updateRequest.price());
                change = true;
            }
            if (updateRequest.quantity() != null && updateRequest.quantity() != product.getQuantity()) {
                product.setQuantity(updateRequest.quantity());
                change = true;
            }
            if (!change){
                throw new RequestValidationException("No data changes found!");
            }
            productDao.updateProduct(product);
    }
    public Integer getQuantity(Integer id){
        return productRepository.findById(id).get().getQuantity();
    }

    /*@RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeMessage(Order order) {
        LOGGER.info(String.format("Message received: %s", order.toString()));
        Integer id = order.getProductId();
        Product product = getProduct(id);
        product.setQuantity(product.getQuantity() - order.getQuantity());
        productDao.updateProduct(product);
    }*/
}
