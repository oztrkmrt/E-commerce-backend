package com.spring.ecommerce.dto;

import com.spring.ecommerce.entity.Order;
import com.spring.ecommerce.entity.Product;
import com.spring.ecommerce.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private long userId;

    private List<Long> productIds;

}
