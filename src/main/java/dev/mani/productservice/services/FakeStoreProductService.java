package dev.mani.productservice.services;

import dev.mani.productservice.dtos.FakeStoreCreateProductDto;
import dev.mani.productservice.dtos.FakeStoreProductDto;
import dev.mani.productservice.dtos.FakeStoreUpdateProductRequestDto;
import dev.mani.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] responseDtos = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : responseDtos) {
            products.add(productDto.toProduct());
        }

        return products;

    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product createProduct(String title, String description, Double price, String image, String category) {
        FakeStoreCreateProductDto requestDto = new FakeStoreCreateProductDto();
        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setPrice(price);
        requestDto.setImage(image);
        requestDto.setCategory(category);

        FakeStoreProductDto responseDto = restTemplate.postForObject("https://fakestoreapi.com/products", requestDto, FakeStoreProductDto.class);

        return responseDto.toProduct();
    }

    public Product updateProductById(Long id, String title, String description, Double price, String image, String category ) {
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

        FakeStoreProductDto productDto = responseDto.getBody();
        return productDto.toProduct();
    }


}
