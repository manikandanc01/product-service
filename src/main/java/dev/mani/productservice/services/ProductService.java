package dev.mani.productservice.services;

import dev.mani.productservice.models.Product;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface ProductService {
    ResponseEntity<Product> getProductById(Long id);
    ResponseEntity<Product> createProduct(String title, String description, Double price, String image, String category);
    ResponseEntity<List<Product>> getAllProducts();
    ResponseEntity<Product> updateProductById(Long id, String title, String description, Double price, String image, String category);
}
