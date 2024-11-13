package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.ProductResponse;
import com.spring.ecommerce.entity.Category;
import com.spring.ecommerce.entity.Product;
import com.spring.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findAll(){
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse findById(@PathVariable long id){
        return productService.findProductById(id);
    }

    @GetMapping("/category")
    public List<ProductResponse> findByCategory(@RequestParam String category) {
        return productService.findProductsByCategory(category);
    }

    @GetMapping("/name")
    public List<ProductResponse> findByName(@RequestParam String name){
        return productService.findProductsByName(name);
    }

    @PostMapping
    public ProductResponse saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ProductResponse deleteProduct(@PathVariable long id){
        return productService.deleteProduct(id);
    }

}
