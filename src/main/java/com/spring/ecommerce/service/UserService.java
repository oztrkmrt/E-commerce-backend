package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    User findByEmail(String email);

}
