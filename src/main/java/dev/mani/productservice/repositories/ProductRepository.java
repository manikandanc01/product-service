package dev.mani.productservice.repositories;

import dev.mani.productservice.models.Product;
import dev.mani.productservice.repositories.projections.ProductTitleAndDesc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    Optional<Product> findByTitleAndCategory_name(String title, String name);

    // HQL query
    @Query("select p from Product p where p.id= :id")
    Product getProductDetails(@Param("id") Long id);

    @Query("select p from Product p where p.category.name= :categoryName")
    List<Product> getProductDetails2(@Param("categoryName") String categoryName);

    @Query(value = "select * from product where id = :id", nativeQuery = true)
    Product getProductDetails3(@Param("id") Long id);

    // projections
    @Query(value = "select title,description from product where id = :id", nativeQuery = true)
    ProductTitleAndDesc getProductTitleAndDescById(@Param("id") Long id);
}
