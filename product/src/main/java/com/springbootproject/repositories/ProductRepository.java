package com.springbootproject.repositories;

import com.springbootproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsProductById(Integer id);
}
