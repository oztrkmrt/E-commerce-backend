package com.spring.ecommerce.dto;

import com.spring.ecommerce.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {

    public static UserResponse converUserToUserResponse(User user){
        return new UserResponse(user.getId(), user.getFullName(), user.getEmail());
    }

    public static List<UserResponse> converUserListToUserResponseList(List<User> users){
        List<UserResponse> responseList = new ArrayList<>();

        users.forEach(user -> {
            responseList.add(new UserResponse(user.getId(), user.getFullName(), user.getEmail()));
        });
        return responseList;
    }

}
