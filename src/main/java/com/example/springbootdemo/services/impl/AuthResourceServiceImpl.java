package com.example.springbootdemo.services.impl;

import com.example.springbootdemo.model.AuthAction;
import com.example.springbootdemo.model.AuthResource;
import com.example.springbootdemo.repository.AuthResourceActionRepository;
import com.example.springbootdemo.repository.AuthResourceRepository;
import com.example.springbootdemo.repository.AuthRoleResourceRepository;
import com.example.springbootdemo.services.AuthResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AuthResourceServiceImpl
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Slf4j
@Service("AuthResourceServiceImpl")
public class AuthResourceServiceImpl implements AuthResourceService {

    @Autowired
    private AuthResourceRepository authResourceRepository;

    @Autowired
    private AuthResourceActionRepository resourceActionRepository;

    @Autowired
    private AuthRoleResourceRepository roleResourceRepository;

    @Override
    public List<AuthResource> findAll() {
        List<AuthResource> resources = authResourceRepository.findAllByDeletedFalse();
        for (AuthResource resource : resources) {
            List<AuthAction> actions = resourceActionRepository.findByResourceId(resource.getId());
            resource.setTrsActions(actions);
        }
        return resources;
    }

}
