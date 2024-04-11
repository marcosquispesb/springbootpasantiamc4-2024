package com.example.springbootdemo.services.impl;

import com.example.springbootdemo.model.AuthRole;
import com.example.springbootdemo.repository.AuthRoleRepository;
import com.example.springbootdemo.services.AuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AuthRoleServiceImpl
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Override
    public List<AuthRole> findAll() {
        List<AuthRole> roles = authRoleRepository.findAll();
        return roles;
    }

}
