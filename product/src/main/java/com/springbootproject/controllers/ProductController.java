package com.springbootproject.controllers;

import com.springbootproject.models.Product;
import com.springbootproject.requests.ProductRegistrationRequest;
import com.springbootproject.requests.ProductUpdateRequest;
import com.springbootproject.services.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("{productId}")
    public Product getProductById(@PathVariable("productId") int productId){
        return productService.getProduct(productId);
    }
    @PostMapping
    public void addProduct(@RequestBody ProductRegistrationRequest request){
        productService.addProduct(request);
    }
    @DeleteMapping("{productId}")
    public void removeProduct(@PathVariable Integer productId){
        productService.removeProduct(productId);
    }
    @PutMapping("{productId}")
    public void updateProduct(@PathVariable Integer productId, @RequestBody ProductUpdateRequest updateRequest){
        productService.updateProduct(productId,updateRequest);
    }
    @GetMapping("quantity/{productId}")
    public Integer getQuantity(@PathVariable Integer productId){
        return productService.getQuantity(productId);
    }

}
