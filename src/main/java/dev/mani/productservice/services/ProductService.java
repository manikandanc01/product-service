package dev.mani.productservice.services;

import dev.mani.productservice.models.Product;


import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    Product createProduct(String title, String description, Double price, String image, String category);
    List<Product> getAllProducts();
    Product updateProductById(Long id, String title, String description, Double price, String image, String category);
}
