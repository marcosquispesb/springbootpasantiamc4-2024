package com.example.springbootdemo.services.impl;

import com.example.springbootdemo.model.AuthAction;
import com.example.springbootdemo.repository.AuthActionRepository;
import com.example.springbootdemo.services.AuthActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.OperationsException;
import java.util.List;
import java.util.Optional;

/**
 * UserServiceImpl
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Service
public class AuthActionServiceImpl implements AuthActionService {

    @Autowired
    private AuthActionRepository authActionRepository;

    @Override
    public List<AuthAction> findAll() {
        return authActionRepository.findAll();
    }

    @Override
    public AuthAction getById(Long id) throws OperationsException {
        Optional<AuthAction> authActionOpt = authActionRepository.findById(id);
        if (!authActionOpt.isPresent())
            throw new OperationsException("No se encontro action para el id = " + id);

        return authActionOpt.get();
    }

    @Override
    public AuthAction findByCode(String code) {
        AuthAction authAction = authActionRepository.findByCode(code);
        return authAction;
    }

    @Override
    public AuthAction getByCode(String code) throws OperationsException {
        Optional<AuthAction> authActionOpt = authActionRepository.getByCode(code);
        if (!authActionOpt.isPresent())
            throw new OperationsException("No se encontro action para el code = " + code);

        return authActionOpt.get();
    }
}
