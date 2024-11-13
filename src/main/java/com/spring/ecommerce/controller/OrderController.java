package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.OrderRequest;
import com.spring.ecommerce.dto.UserResponse;
import com.spring.ecommerce.entity.Order;
import com.spring.ecommerce.entity.Product;
import com.spring.ecommerce.entity.User;
import com.spring.ecommerce.service.OrderService;
import com.spring.ecommerce.service.ProductService;
import com.spring.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;
    private UserService userService;
    private ProductService productService;

    @GetMapping()
    public List<Order> findAll(){
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable long id){
        return orderService.findOrderById(id);
    }

    @GetMapping("/customer/{id}")
    public List<Order> findByCustomerId(@PathVariable long id){
        return orderService.findOrdersByCustomerId(id);
    }

    @GetMapping("/customer/name")
    public List<Order> findByCustomerName(@RequestParam String name){
        return orderService.findOrdersByCustomerName(name);
    }

    @PostMapping
    public Order saveOrder(@RequestBody OrderRequest orderRequest){
        User orderingUser = userService.findUserEntityById(orderRequest.getUserId());

        Order savedOrder = new Order();
        savedOrder.setUser(orderingUser);
        savedOrder.setDateTime(LocalDateTime.now());

        for (long productId : orderRequest.getProductIds()){
            Product product = productService.findProductEntityById(productId);
            savedOrder.addProducts(product);
        }
        return orderService.saveOrder(savedOrder);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable long id, @RequestBody Order order){
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public Order deleteOrder(@PathVariable long id){
        return orderService.deleteOrder(id);
    }
}
