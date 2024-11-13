package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.DtoConverter;
import com.spring.ecommerce.dto.UserResponse;
import com.spring.ecommerce.entity.Role;
import com.spring.ecommerce.entity.User;
import com.spring.ecommerce.exceptions.EcommerceException;
import com.spring.ecommerce.repository.RoleRepository;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse UserRegister(String fullName, String email, String password){
        Optional<User> foundedUser = userRepository.findByEmail(email);
        if (foundedUser.isPresent()) {
            throw new EcommerceException("User with this email already exist: " + email, HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setAuthorities(roles);

        return DtoConverter.convertUserToUserResponse(userRepository.save(user));

    }

    public UserResponse AdminRegister(String fullName, String email, String password){
        Optional<User> foundedUser = userRepository.findByEmail(email);
        if (foundedUser.isPresent()) {
            throw new EcommerceException("User with this email already exist: " + email, HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(password);
        Role adminRole = roleRepository.findByAuthority("ADMIN").get();

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setAuthorities(roles);

        return DtoConverter.convertUserToUserResponse(userRepository.save(user));

    }
}
