package dev.mani.productservice.controllers;

import dev.mani.productservice.models.Product;
import dev.mani.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public void getAllProducts(){

    }

    public void createProduct(){

    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
      return productService.getProductById(id);
    }
}
