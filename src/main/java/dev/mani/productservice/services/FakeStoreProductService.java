package dev.mani.productservice.services;

import dev.mani.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        Product product =  restTemplate.getForObject("https://fakestoreapi.com/products/"+id, Product.class);
        return product;
    }
}
