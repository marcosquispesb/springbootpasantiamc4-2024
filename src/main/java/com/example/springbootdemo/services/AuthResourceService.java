package com.example.springbootdemo.services;

import com.example.springbootdemo.model.AuthResource;
import com.example.springbootdemo.rest.dto.AuthResourceDto;

import java.util.List;

/**
 * AuthResourceService
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface AuthResourceService {

    List<AuthResource> findAll();

}
