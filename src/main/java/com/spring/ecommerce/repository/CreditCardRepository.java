package com.spring.ecommerce.repository;

import com.spring.ecommerce.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
