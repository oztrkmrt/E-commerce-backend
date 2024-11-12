package com.spring.ecommerce.repository;

import com.spring.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findAllByCategory(@Param("category") String category);


    @Query("SELECT p FROM Product p WHERE p.name ILIKE %:name%")
    List<Product> findAllByName(@Param("name") String name);



}
