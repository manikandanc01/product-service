package dev.mani.productservice.services;

import dev.mani.productservice.dtos.FakeStoreCreateProductDto;
import dev.mani.productservice.dtos.FakeStoreProductDto;
import dev.mani.productservice.dtos.FakeStoreUpdateProductRequestDto;
import dev.mani.productservice.exceptions.ExternalServiceException;
import dev.mani.productservice.exceptions.NoProductsFoundException;
import dev.mani.productservice.exceptions.ProductNotFoundException;
import dev.mani.productservice.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        ResponseEntity<List<FakeStoreProductDto>> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new ExternalServiceException("Error while calling external service");
        }

        List<FakeStoreProductDto> responseDTOS = responseEntity.getBody();
        if(responseDTOS == null) {
            throw new NullPointerException();
        }

        if(responseDTOS.isEmpty()) {
            throw new NoProductsFoundException();
        }

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : responseDTOS) {
            products.add(productDto.toProduct());
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) {
        ResponseEntity<FakeStoreProductDto> responseEntity =  restTemplate.getForEntity("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product not found");
        }
        Product product = fakeStoreProductDto.toProduct();
        return  new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> createProduct(String title, String description, Double price, String image, String category) {
        FakeStoreCreateProductDto requestDto = new FakeStoreCreateProductDto();
        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setPrice(price);
        requestDto.setImage(image);
        requestDto.setCategory(category);

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity("https://fakestoreapi.com/products", requestDto, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        if(fakeStoreProductDto == null) {
            throw new NullPointerException();
        }
        Product product = fakeStoreProductDto.toProduct();
        return   new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    public ResponseEntity<Product> updateProductById(Long id, String title, String description, Double price, String image, String category ) {
        FakeStoreUpdateProductRequestDto requestDto = new FakeStoreUpdateProductRequestDto();
        requestDto.setId(id);
        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setPrice(price);
        requestDto.setImage(image);
        requestDto.setCategory(category);

        HttpEntity<FakeStoreUpdateProductRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<FakeStoreProductDto> responseDto = restTemplate.exchange(
                "https://fakestoreapi.com/products/"+id,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = responseDto.getBody();
        if(fakeStoreProductDto == null) {
            throw new NullPointerException();
        }
        Product product = fakeStoreProductDto.toProduct();
        return   new ResponseEntity<>(product, HttpStatus.OK);
    }

}
