package com.spring.ecommerce.exceptions;

import java.time.LocalDateTime;

public record EcommerceErrorResponse(Integer status, String message, LocalDateTime localDateTime) {
}
