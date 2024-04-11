package com.example.springbootdemo.services;

import com.example.springbootdemo.model.AuthUser;
import com.example.springbootdemo.rest.dto.AuthUserDto;

import java.util.List;

/**
 * AuthUserService
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface AuthUserService {

    List<AuthUser> findAll();

    List<AuthUserDto> findAllDto();

    Long save(AuthUserDto dto);

    void delete(Long id);
}
