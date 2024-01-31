package com.springbootproject.dao;

import com.springbootproject.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> selectAllProducts();

    Optional<Product> selectProductById(Integer id);
    void insertProduct(Product product);
    void deleteProduct(Integer id);
    boolean existProduct(Integer id);
    void updateProduct(Product product);
}
