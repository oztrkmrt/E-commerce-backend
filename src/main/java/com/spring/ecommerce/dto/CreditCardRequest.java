package com.spring.ecommerce.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class CreditCardRequest {

    private String fullName;

    private String cardNo;

    private String expireDate;

    private String cvv;

    private long userId;
}

