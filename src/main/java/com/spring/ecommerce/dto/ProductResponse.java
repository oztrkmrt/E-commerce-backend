package com.spring.ecommerce.dto;

import com.spring.ecommerce.entity.Category;
import com.spring.ecommerce.entity.Gender;

public record ProductResponse(long id, String name, double price, int stock, Gender gender, Category category, String imgUrl) {
}
