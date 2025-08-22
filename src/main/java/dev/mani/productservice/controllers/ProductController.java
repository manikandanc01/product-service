package dev.mani.productservice.controllers;

import dev.mani.productservice.dtos.CreateProductRequestDto;
import dev.mani.productservice.dtos.UpdateProductRequestDto;
import dev.mani.productservice.models.Product;
import dev.mani.productservice.services.ProductService;
import dev.mani.productservice.services.ProductServiceFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductServiceFactory productServiceFactory;

    public ProductController(ProductServiceFactory productServiceFactory) {
        this.productServiceFactory = productServiceFactory;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(value = "is_database", defaultValue = "true") boolean isDatabase){
        ProductService productService = productServiceFactory.getService(isDatabase);
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id,
                                                  @RequestParam(value = "is_database", defaultValue = "true") boolean isDatabase){
        ProductService productService = productServiceFactory.getService(isDatabase);
        return  productService.getProductById(id);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody  CreateProductRequestDto createProductRequestDto,
                                                 @RequestParam(value = "is_database", defaultValue = "true") boolean isDatabase){
         ProductService productService = productServiceFactory.getService(isDatabase);
         return productService.createProduct(createProductRequestDto.getTitle(),
                  createProductRequestDto.getDescription(),
                  createProductRequestDto.getPrice(),
                  createProductRequestDto.getImage(), createProductRequestDto.getCategory());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody UpdateProductRequestDto updateProductRequestDto,
                                                 @RequestParam(value = "is_database", defaultValue = "true") boolean isDatabase){
        ProductService productService = productServiceFactory.getService(isDatabase);
        return productService.updateProductById(
                id,
                updateProductRequestDto.getTitle(),
                updateProductRequestDto.getDescription(),
                updateProductRequestDto.getPrice(),
                updateProductRequestDto.getImage(),
                updateProductRequestDto.getCategory());
    }


}
