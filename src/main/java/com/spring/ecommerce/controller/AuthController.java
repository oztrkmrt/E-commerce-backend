package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.RegisterUser;
import com.spring.ecommerce.dto.UserResponse;
import com.spring.ecommerce.entity.User;
import com.spring.ecommerce.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody RegisterUser registerUser){
        return authenticationService.UserRegister(registerUser.fullName(), registerUser.email(), registerUser.password());
    }

    @PostMapping("/registerAdmin")
    public UserResponse registerAdmin(@RequestBody RegisterUser registerUser){
        return authenticationService.AdminRegister(registerUser.fullName(), registerUser.email(), registerUser.password());
    }


}
