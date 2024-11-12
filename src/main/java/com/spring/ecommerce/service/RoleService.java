package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.Role;

import java.util.Optional;

public interface RoleService {

    Role findByAuthority(String authority);

}
