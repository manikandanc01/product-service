package dev.mani.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}
