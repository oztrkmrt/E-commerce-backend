package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.DtoConverter;
import com.spring.ecommerce.dto.ProductResponse;
import com.spring.ecommerce.entity.Category;
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
    public List<ProductResponse> findAllProducts() {
        return DtoConverter.convertProductListToProductResponseList(productRepository.findAll());
    }

    @Override
    public ProductResponse findProductById(Long id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()){
            return DtoConverter.convertProductToProductResponse(foundProduct.get());
        }
        throw new EcommerceException("Product with given id not exist: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Product findProductEntityById(Long id) {
        Product foundProduct = productRepository
                .findById(id)
                .orElseThrow(()->{
                    throw new EcommerceException("Product with given id not exist: " + id, HttpStatus.NOT_FOUND);
                });
        return foundProduct;
    }

    @Override
    public ProductResponse saveProduct(Product product) {
        return DtoConverter.convertProductToProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Long id, Product updatedProduct) {
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

            return DtoConverter.convertProductToProductResponse(productRepository.save(existingProduct));
        } else {
            throw new EcommerceException("Product with given id not exist: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ProductResponse deleteProduct(Long id) {
        Product deletedProduct = productRepository
                .findById(id)
                .orElseThrow(()-> {
                            throw new EcommerceException("Product with given id not exist: " + id, HttpStatus.NOT_FOUND);
                        });
        productRepository.delete(deletedProduct);
        return DtoConverter.convertProductToProductResponse(deletedProduct);
    }

    @Override
    public List<ProductResponse> findProductsByCategory(String category) {
        Category categoryname = Category.valueOf(category.toUpperCase());
        return DtoConverter.convertProductListToProductResponseList(productRepository.findAllByCategory(categoryname));
    }

    @Override
    public List<ProductResponse> findProductsByName(String name) {
        return DtoConverter.convertProductListToProductResponseList(productRepository.findAllByName(name));
    }
}
