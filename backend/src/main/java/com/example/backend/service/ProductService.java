package com.example.backend.repository;

import com.example.backend.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product saveProduct(Product Product);
    Product updateProduct(Product Product);
    void deleteProduct(Long id);
}