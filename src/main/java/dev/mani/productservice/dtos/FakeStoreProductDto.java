package dev.mani.productservice.dtos;

import dev.mani.productservice.models.Category;
import dev.mani.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;

    public Product toProduct() {
        Product product = new Product();
        Category category = new Category();
        category.setName(this.category);
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setCategory(category);
        return product;
    }
}
