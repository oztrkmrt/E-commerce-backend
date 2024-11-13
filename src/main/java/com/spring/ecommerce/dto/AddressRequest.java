package com.spring.ecommerce.dto;

import lombok.Data;

@Data
public class AddressRequest {

    private String title;

    private String description;

    private long userId;
}
