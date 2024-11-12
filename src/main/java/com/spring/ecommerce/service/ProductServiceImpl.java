package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.Product;
import com.spring.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product addProduct(Product product) {
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
        } else{
            throw new RuntimeException("Product not found: " + updatedProduct);
        }
    }

    @Override
    public Product deleteProduct(Long id) {
        Product deletedProduct = getProductById(id).orElseThrow(()->new RuntimeException("Product with given id is not exist: " + id));
        productRepository.delete(deletedProduct);
        return deletedProduct;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findAllByName(name);
    }
}
