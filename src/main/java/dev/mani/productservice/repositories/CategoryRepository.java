package dev.mani.productservice.repositories;

import dev.mani.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
//    Category save(Category category);
}
