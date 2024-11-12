package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.DtoConverter;
import com.spring.ecommerce.dto.UserResponse;
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
    public List<UserResponse> findAll() {
        return DtoConverter.converUserListToUserResponseList(userRepository.findAll());
    }

    @Override
    public UserResponse findByEmail(String email) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(()-> {
                    //TODO: Exception handling yap
                    throw new RuntimeException("User with given email not exist: " + email);
                });
        return DtoConverter.converUserToUserResponse(user);
    }

    @Override
    public UserResponse findUserById(long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> {
                    //TODO: Exception handling yap
                    throw new RuntimeException("User with given id not exist: " + id);
                });
        return DtoConverter.converUserToUserResponse(user);
    }

    @Override
    public UserResponse saveUser(User user) {
        return DtoConverter.converUserToUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(long id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isPresent()){
            User existingUser = existingUserOpt.get();
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());

            return DtoConverter.converUserToUserResponse(userRepository.save(existingUser));
        } else {
            //TODO: Exception handling ister
            throw new RuntimeException("User not found: " + updatedUser);
        }
    }

    @Override
    public UserResponse deleteUser(long id) {
        User deletedUser = userRepository
                .findById(id)
                .orElseThrow(()-> {
            //TODO: Exception handling yap
            throw new RuntimeException("User with given id not exist: " + id);
        });
        userRepository.delete(deletedUser);
        return DtoConverter.converUserToUserResponse(deletedUser);
    }
}
