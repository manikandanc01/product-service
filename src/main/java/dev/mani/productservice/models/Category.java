package dev.mani.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;

    // without this mappedBy -> spring jpa creates one more mapping table
    // we have to inform spring this cardinality is already handled no need to create the mapping table for this.
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    List<Product> products;
}
