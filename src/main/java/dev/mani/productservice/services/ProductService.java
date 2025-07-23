package dev.mani.productservice.services;

import dev.mani.productservice.models.Product;
import org.springframework.stereotype.Service;

public interface ProductService {
    Product getProductById(Long id);
}
