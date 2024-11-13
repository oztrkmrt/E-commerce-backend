package com.spring.ecommerce.dto;

import com.spring.ecommerce.entity.Product;
import com.spring.ecommerce.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {

    public static UserResponse convertUserToUserResponse(User user){
        return new UserResponse(user.getId(), user.getFullName(), user.getEmail());
    }

    public static List<UserResponse> convertUserListToUserResponseList(List<User> users){
        List<UserResponse> responseList = new ArrayList<>();

        users.forEach(user -> {
            responseList.add(new UserResponse(user.getId(), user.getFullName(), user.getEmail()));
        });
        return responseList;
    }

    public static ProductResponse convertProductToProductResponse (Product product){
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getGender(), product.getCategory(), product.getImgUrl());
    }

    public static List<ProductResponse> convertProductListToProductResponseList (List<Product> products){
        List<ProductResponse> responseList = new ArrayList<>();

        products.forEach(product -> {
            responseList.add(new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getGender(), product.getCategory(), product.getImgUrl()));
        });

        return responseList;
    }

}
