package dev.mani.productservice.services;

import dev.mani.productservice.exceptions.NoProductsFoundException;
import dev.mani.productservice.exceptions.ProductNotFoundException;
import dev.mani.productservice.models.Category;
import dev.mani.productservice.models.Product;
import dev.mani.productservice.repositories.CategoryRepository;
import dev.mani.productservice.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("databaseProductService")
public class DatabaseProductService implements  ProductService{
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public DatabaseProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public ResponseEntity<Product> getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }

        Product product = productOptional.get();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> createProduct(String title, String description, Double price, String image, String categoryName) {
        Product product  = new Product();
        return getProductResponseEntity(title, description, price, image, categoryName, product);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()) {
            throw new NoProductsFoundException();
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> updateProductById(Long id, String title, String description, Double price, String image, String category) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }

        Product product = productOptional.get();
        return getProductResponseEntity(title, description, price, image, category, product);
    }

    private ResponseEntity<Product> getProductResponseEntity(String title, String description, Double price, String image, String category, Product product) {
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
        Category categoryFromDatabase = categoryRepository.findByName(category);
        if(categoryFromDatabase == null) {
            Category newCategory = new Category();
            newCategory.setName(category);
            categoryFromDatabase = newCategory;
        }
        product.setCategory(categoryFromDatabase);

        Product productSaved =  productRepository.save(product);
        return new ResponseEntity<>(productSaved, HttpStatus.OK);
    }
}
