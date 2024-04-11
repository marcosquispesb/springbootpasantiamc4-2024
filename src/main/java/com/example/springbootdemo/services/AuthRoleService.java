package com.example.springbootdemo.services;

import com.example.springbootdemo.model.AuthRole;

import java.util.List;

/**
 * AuthRoleService
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface AuthRoleService {

    List<AuthRole> findAll();
}
