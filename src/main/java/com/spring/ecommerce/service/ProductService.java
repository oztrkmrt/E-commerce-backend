package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProducts();

    Product findProductById(Long id);

    Product saveProduct(Product product);

    Product updateProduct(Long id, Product product);

    Product deleteProduct(Long id);

    List<Product> findProductsByCategory(String category);

    List<Product> findProductsByName(String name);
}
