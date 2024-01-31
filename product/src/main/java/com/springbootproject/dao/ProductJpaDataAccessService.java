package com.springbootproject.dao;

import com.springbootproject.models.Product;
import com.springbootproject.repositories.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductJpaDataAccessService implements ProductDao{
    private final ProductRepository productRepository;

    public ProductJpaDataAccessService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> selectAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> selectProductById(Integer id) {
        return productRepository.findById(id);
    }


    @Override
    public void insertProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean existProduct(Integer id) {
        return productRepository.existsProductById(id);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

}
