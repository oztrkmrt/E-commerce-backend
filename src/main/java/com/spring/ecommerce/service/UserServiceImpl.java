package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.DtoConverter;
import com.spring.ecommerce.dto.UserResponse;
import com.spring.ecommerce.entity.User;
import com.spring.ecommerce.exceptions.EcommerceException;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> findAll() {
        return DtoConverter.convertUserListToUserResponseList(userRepository.findAll());
    }

    @Override
    public UserResponse findUserById(long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> {
                    throw new EcommerceException("User with given id not exist: " + id, HttpStatus.NOT_FOUND);
                });
        return DtoConverter.convertUserToUserResponse(user);
    }

    @Override
    public User findUserEntityById(long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> {
                    throw new EcommerceException("User with given id not exist: " + id, HttpStatus.NOT_FOUND);
                });
        return user;
    }

    @Override
    public UserResponse saveUser(User user) {
        return DtoConverter.convertUserToUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(long id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isPresent()){
            User existingUser = existingUserOpt.get();
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());

            return DtoConverter.convertUserToUserResponse(userRepository.save(existingUser));
        } else {
            throw new EcommerceException("User not exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UserResponse deleteUser(long id) {
        User deletedUser = userRepository
                .findById(id)
                .orElseThrow(()-> {
                    throw new EcommerceException("User with given id not exist: " + id, HttpStatus.NOT_FOUND);
        });
        userRepository.delete(deletedUser);
        return DtoConverter.convertUserToUserResponse(deletedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> {
                throw new UsernameNotFoundException("User credentials are not valid");
        });
    }
}


