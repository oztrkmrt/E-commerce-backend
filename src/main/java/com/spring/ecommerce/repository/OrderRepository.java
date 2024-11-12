package com.spring.ecommerce.repository;

import com.spring.ecommerce.entity.Order;
import com.spring.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.user = :user")
    List<Order> findOrderByCustomer(@Param("user")User user);

//    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
//    List<Order> findOrderByCustomerId(@Param("userId") Long userId);


}
