package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.Product;
import com.spring.ecommerce.exceptions.EcommerceException;
import com.spring.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()){
            return foundProduct.get();
        }
        throw new EcommerceException("Product with given id not exist: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProductOpt = productRepository.findById(id);

        if (existingProductOpt.isPresent()){
            Product existingProduct = existingProductOpt.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStock(updatedProduct.getStock());
            existingProduct.setGender(updatedProduct.getGender());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setImgUrl(updatedProduct.getImgUrl());

            return productRepository.save(existingProduct);
        } else {
            throw new EcommerceException("Product with given id not exist: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Product deleteProduct(Long id) {
        Product deletedProduct = findProductById(id);
        productRepository.delete(deletedProduct);
        return deletedProduct;
    }

    @Override
    public List<Product> findProductsByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return productRepository.findAllByName(name);
    }
}
