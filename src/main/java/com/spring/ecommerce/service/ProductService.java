package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.ProductResponse;
import com.spring.ecommerce.entity.Category;
import com.spring.ecommerce.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductResponse> findAllProducts();

    ProductResponse findProductById(Long id);

    Product findProductEntityById(Long id);

    ProductResponse saveProduct(Product product);

    ProductResponse updateProduct(Long id, Product product);

    ProductResponse deleteProduct(Long id);

    List<ProductResponse> findProductsByCategory(String category);

    List<ProductResponse> findProductsByName(String name);
}
