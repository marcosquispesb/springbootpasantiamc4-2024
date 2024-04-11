package com.example.springbootdemo.services.impl;

import com.example.springbootdemo.model.AuthRole;
import com.example.springbootdemo.model.AuthUser;
import com.example.springbootdemo.repository.AuthRoleRepository;
import com.example.springbootdemo.repository.AuthUserRepository;
import com.example.springbootdemo.rest.dto.AuthUserDto;
import com.example.springbootdemo.rest.exceptions.DataNotFoundException;
import com.example.springbootdemo.rest.exceptions.OperationException;
import com.example.springbootdemo.services.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * AuthUserServiceImpl
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Service
@Slf4j
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthRoleRepository authRoleRepository;


    @Override
    public List<AuthUser> findAll() {
        List<AuthUser> result = authUserRepository.findAllByDeletedFalse();
        return result;
    }

    @Override
    public List<AuthUserDto> findAllDto() {
        List<AuthUser> result = authUserRepository.findAllByDeletedFalse();
        return result.stream().map(x -> {
            AuthUserDto dto = new AuthUserDto(x);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long save(AuthUserDto dto) {
        // VALIDATION
        if (dto.getRole() == null)
            throw new OperationException("Es requerido el rol");

        AuthRole role = authRoleRepository.findById(dto.getRole().getId())
                .orElseThrow(() -> new DataNotFoundException("No se encontro el el rol a asignar"));

        if (dto.getId() == null || dto.getId() <= 0) { // nuevo

            AuthUser user = new AuthUser();
            BeanUtils.copyProperties(dto, user);
            user.setRole(role);
            user = authUserRepository.save(user);
            dto.setId(user.getId());

        } else { // update
            Optional<AuthUser> userOpt = authUserRepository.findByIdAndDeletedFalse(dto.getId());
            if (!userOpt.isPresent())
                throw new DataNotFoundException("No se encontro el user con id: " + dto.getId());

            AuthUser user = userOpt.get();
            user.setName(dto.getName());
            user.setUsername(dto.getUsername());
            user.setPassword(dto.getPassword());
            //user.setBirthDay(dto.getBirthDay());
            user.setRole(role);
            authUserRepository.save(user);
        }
        return dto.getId();
    }

    @Override
    public void delete(Long id) {
        Optional<AuthUser> opt = authUserRepository.findByIdAndDeletedFalse(id);
        if (!opt.isPresent())
            throw new DataNotFoundException("No se encontro el user a eliminar con id: " + id);

        opt.get().setDeleted(true);
        authUserRepository.save(opt.get());
    }
}
