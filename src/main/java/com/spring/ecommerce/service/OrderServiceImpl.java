package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.OrderRequest;
import com.spring.ecommerce.entity.Order;
import com.spring.ecommerce.entity.User;
import com.spring.ecommerce.exceptions.EcommerceException;
import com.spring.ecommerce.repository.OrderRepository;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(long id) {
        Optional<Order> foundOrder = orderRepository.findById(id);
        if (foundOrder.isPresent()){
            return foundOrder.get();
        }
        throw new EcommerceException("Order with given id not exist: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order updatedOrder) {
        Order existingOrder = findOrderById(id);
        existingOrder.setDateTime(updatedOrder.getDateTime());
        existingOrder.setUser(updatedOrder.getUser());

        return orderRepository.save(existingOrder);
    }

    @Override
    public Order deleteOrder(Long id) {
        Order deletedOrder = findOrderById(id);
        orderRepository.delete(deletedOrder);
        return deletedOrder;
    }

    @Override
    public List<Order> findOrdersByCustomerId(long id) {
        return orderRepository.findOrderByCustomerId(id);
    }

    @Override
    public List<Order> findOrdersByCustomerName(String name) {
        return orderRepository.findOrderByCustomerName(name);
    }
}
