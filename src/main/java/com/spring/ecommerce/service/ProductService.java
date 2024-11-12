package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product addProduct(Product product);

    Product updateProduct(Long id, Product product);

    Product deleteProduct(Long id);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByName(String name);
}
