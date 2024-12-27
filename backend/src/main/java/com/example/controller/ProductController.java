package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Product;
import com.example.backend.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService ProductService;

    @GetMapping
    public List<Product> getAllProducts(){
        return ProductService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> product = ProductService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(
            ()-> ResponseEntity.notFound().build()
        );
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return ProductService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetail){
        Optional<Product> product = ProductService.getProductById(id);
        if(product.isPresent()){
            Product productNew = product.get();
            productNew.setName(productDetail.getName());
            productNew.setDescription(productDetail.getDescription());
            productNew.setPrice(productDetail.getPrice());
            return ResponseEntity.ok(ProductService.updateProduct(productNew));
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        Optional<Product> product = ProductService.getProductById(id);
        if(product.isPresent()){
            ProductService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }
}
