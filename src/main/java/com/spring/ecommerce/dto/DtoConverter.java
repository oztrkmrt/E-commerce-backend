package com.spring.ecommerce.dto;

import com.spring.ecommerce.entity.CreditCard;
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

    public static CreditCardResponse convertCardToCardResponse(CreditCard creditCard){
        return new CreditCardResponse(creditCard.getFullName(), creditCard.getCardNo(), creditCard.getExpireDate());
    }

    public static List<CreditCardResponse> convertCardListToCardResponseList(List<CreditCard> creditCards){
        List<CreditCardResponse> responseList = new ArrayList<>();

        creditCards.forEach(creditCard -> {
            responseList.add(new CreditCardResponse(creditCard.getFullName(), creditCard.getCardNo(), creditCard.getExpireDate()));
        });
        return responseList;
    }

}
