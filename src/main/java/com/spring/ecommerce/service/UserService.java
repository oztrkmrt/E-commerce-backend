package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.UserResponse;
import com.spring.ecommerce.entity.User;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll();

    UserResponse findByEmail(String email);

    UserResponse findUserById(long id);

    User findUserEntityById(long id);

    UserResponse saveUser(User user);

    UserResponse updateUser(long id, User user);

    UserResponse deleteUser(long id);

}
