package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.Role;
import com.spring.ecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByAuthority(String authority) {
        Optional<Role> optionalAuthority = roleRepository.findByAuthority(authority);
        if (optionalAuthority.isPresent()){
            return optionalAuthority.get();
        }
        //TODO: Exception handling yap
        return null;
    }
}
