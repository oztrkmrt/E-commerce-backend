package com.spring.ecommerce.repository;

import com.spring.ecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
