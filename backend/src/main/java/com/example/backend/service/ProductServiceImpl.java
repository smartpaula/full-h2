package com.example.backend.service;

import com.example.backend.model.Product;
import com.example.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @AutoWired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    };

    @Override
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    };

    @Override
    public Product saveProduct(Product Product){
        return productRepository.save(product);
    };

    @Override
    public updateProduct(Product Product){
        return productRepository.save(product);
    };

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    };
}
