package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User findByEmail(String email);

    User saveUser(User user);

    User updateUser(String email, User user);

    User deleteUser(String email);

}
