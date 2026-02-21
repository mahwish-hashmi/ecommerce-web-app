package com.telusko.ecom_proj.repo;

import com.telusko.ecom_proj.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    /**
     * Case-insensitive full-text search across name, description, brand and category.
     * Uses JPQL so it's database-agnostic (works on MySQL and any future migration).
     * The @Param annotation is required when -parameters compiler flag is not set.
     */
    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.name)        LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.brand)       LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.category)    LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(@Param("keyword") String keyword);
}