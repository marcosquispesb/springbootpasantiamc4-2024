package com.example.springbootdemo.services;

import com.example.springbootdemo.model.AuthAction;

import javax.management.OperationsException;
import java.util.List;

/**
 * AuthActionService
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface AuthActionService {
    List<AuthAction> findAll();

    AuthAction getById(Long id) throws OperationsException;
    AuthAction findByCode(String code);

    AuthAction getByCode(String code) throws OperationsException;
}
