package com.skill.repository;

import com.skill.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 2a. Derived query method: findByCategory
    List<Product> findByCategory(String category);

    // 2b. Derived query method: findByPriceBetween
    List<Product> findByPriceBetween(double min, double max);

    // 3a. JPQL query: sorting products by price
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllSortedByPrice();

    // 3b. JPQL query: fetching products above a price value
    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findProductsExpensiveThan(@Param("price") double price);

    // 3c. JPQL query: fetching products by category
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findProductsByCategoryJPQL(@Param("category") String category);
}
