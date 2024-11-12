package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.User;
import com.spring.ecommerce.repository.AddressRepository;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private AddressRepository addressRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        //TODO: Exception handling yap
        return null;
    }
}
