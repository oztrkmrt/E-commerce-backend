package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.UserResponse;
import com.spring.ecommerce.entity.User;
import com.spring.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findById(@PathVariable long id){
        return userService.findUserById(id);
    }

    @PostMapping
    public UserResponse saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public UserResponse deleteUser(@PathVariable long id){
        return userService.deleteUser(id);
    }
}
