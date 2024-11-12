package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.CreditCard;
import com.spring.ecommerce.entity.Order;
import com.spring.ecommerce.entity.User;

import java.util.List;

public interface OrderService {

    List<Order> findAllOrders();

    Order findOrderById(long id);

    Order updateOrder(Long id, Order order);

    Order deleteOrder(Long id);

    List<Order> findOrdersByCustomer(User user);

}
