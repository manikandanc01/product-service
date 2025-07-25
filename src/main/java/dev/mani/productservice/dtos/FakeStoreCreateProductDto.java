package dev.mani.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductDto {
    public String title;
    public String description;
    public Double price;
    public String category;
    public String image;
}
