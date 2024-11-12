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

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

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
    public User findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        //TODO: Exception handling yap
        return null;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String email, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findByEmail(email);

        if (existingUserOpt.isPresent()){
            User existingUser = existingUserOpt.get();
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());

            return userRepository.save(existingUser);
        } else {
            //TODO: Exception handling ister
            throw new RuntimeException("User not found: " + updatedUser);
        }
    }

    @Override
    public User deleteUser(String email) {
        User deletedUser = findByEmail(email);
        userRepository.delete(deletedUser);
        return deletedUser;
    }
}
