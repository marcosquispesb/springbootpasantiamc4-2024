package com.example.springbootdemo.services.impl;

import com.example.springbootdemo.model.AuthAction;
import com.example.springbootdemo.model.AuthResource;
import com.example.springbootdemo.repository.AuthResourceActionRepository;
import com.example.springbootdemo.repository.AuthResourceRepository;
import com.example.springbootdemo.repository.AuthRoleResourceRepository;
import com.example.springbootdemo.rest.dto.AuthActionDto;
import com.example.springbootdemo.rest.dto.AuthResourceDto;
import com.example.springbootdemo.services.AuthResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    public List<AuthResourceDto> getMenu() {
        log.info("getMenu...");
        List<AuthResource> resources = authResourceRepository.findAllByDeletedFalse();

        // recursos con padre null
        List<AuthResourceDto> fathers = resources.stream()
                .filter(x -> Objects.equals(x.getRecursoPadre(), null))
                .sorted(Comparator.comparingInt(AuthResource::getMenuPosition))
                .map(x -> fillMenuTree(new AuthResourceDto(x), resources)) // recursion
                .collect(Collectors.toList());

        return fathers;
    }

    private AuthResourceDto fillMenuTree(AuthResourceDto dto, List<AuthResource> resources) {
        List<AuthActionDto> actions = resourceActionRepository.findDtosByResourceId(dto.getId());
        dto.setActions(actions);

        // recursos hijos
        List<AuthResourceDto> children = resources.stream()
                .filter(x -> x.getRecursoPadre() != null && Objects.equals(x.getRecursoPadre().getId(), dto.getId()))
                .sorted(Comparator.comparingInt(AuthResource::getMenuPosition))
                .map(x-> fillMenuTree(new AuthResourceDto(x), resources)) // recursion
                .collect(Collectors.toList());
        dto.setChildren(children);

        return dto;
    }
}
