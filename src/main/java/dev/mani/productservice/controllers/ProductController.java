package dev.mani.productservice.controllers;

import dev.mani.productservice.dtos.CreateProductRequestDto;
import dev.mani.productservice.dtos.UpdateProductRequestDto;
import dev.mani.productservice.models.Product;
import dev.mani.productservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
       return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        return  productService.getProductById(id);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody  CreateProductRequestDto createProductRequestDto){
         return productService.createProduct(createProductRequestDto.getTitle(),
                  createProductRequestDto.getDescription(),
                  createProductRequestDto.getPrice(),
                  createProductRequestDto.getImage(), createProductRequestDto.getCategory());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody UpdateProductRequestDto updateProductRequestDto){
        return productService.updateProductById(
                id,
                updateProductRequestDto.getTitle(),
                updateProductRequestDto.getDescription(),
                updateProductRequestDto.getPrice(),
                updateProductRequestDto.getImage(),
                updateProductRequestDto.getCategory());
    }


}
