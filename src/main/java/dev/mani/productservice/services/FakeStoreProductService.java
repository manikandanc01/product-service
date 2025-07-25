package dev.mani.productservice.services;

import dev.mani.productservice.dtos.FakeStoreCreateProductDto;
import dev.mani.productservice.dtos.FakeStoreProductDto;
import dev.mani.productservice.dtos.FakeStoreUpdateProductRequestDto;
import dev.mani.productservice.models.Product;
import org.apache.coyote.Response;
import org.springframework.http.*;
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
    public ResponseEntity<List<Product>> getAllProducts() {
        FakeStoreProductDto[] responseDtos = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : responseDtos) {
            products.add(productDto.toProduct());
        }

        ResponseEntity<List<Product>> response = new ResponseEntity<>(products, HttpStatus.OK);
        return response;

    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) {
        ResponseEntity<FakeStoreProductDto> responseEntity =  restTemplate.getForEntity("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        if(responseEntity.getStatusCode() != HttpStatusCode.valueOf(200)){
            // throw some Exception
        }

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        Product product = fakeStoreProductDto.toProduct();

        ResponseEntity<Product> response = new ResponseEntity<>(product, HttpStatus.OK);
        return response;
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
        if(responseEntity.getStatusCode() != HttpStatusCode.valueOf(200)){
            // throw some Exception
        }

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        Product product = fakeStoreProductDto.toProduct();

        ResponseEntity<Product> response = new ResponseEntity<>(product, HttpStatus.CREATED);
        return response;
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
        Product product = fakeStoreProductDto.toProduct();

        ResponseEntity<Product> response = new ResponseEntity<>(product, HttpStatus.OK);
        return response;
    }


}
