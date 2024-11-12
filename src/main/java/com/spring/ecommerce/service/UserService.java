package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(long id);

}
