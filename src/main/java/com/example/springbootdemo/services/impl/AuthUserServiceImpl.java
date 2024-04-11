package com.example.springbootdemo.services.impl;

import com.example.springbootdemo.model.AuthRole;
import com.example.springbootdemo.model.AuthRoleUser;
import com.example.springbootdemo.model.AuthUser;
import com.example.springbootdemo.repository.AuthRoleRepository;
import com.example.springbootdemo.repository.AuthRoleUserRepository;
import com.example.springbootdemo.repository.AuthUserRepository;
import com.example.springbootdemo.rest.dto.AuthUserDto;
import com.example.springbootdemo.rest.exceptions.DataNotFoundException;
import com.example.springbootdemo.rest.exceptions.OperationException;
import com.example.springbootdemo.services.AuthUserService;
import com.example.springbootdemo.utils.DBUtil;
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

    @Autowired
    private AuthRoleUserRepository authRoleUserRepository;

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

        // SAVE USER
        AuthUser user;
        if (!DBUtil.isValidId(dto)) { // nuevo
            user = new AuthUser();
            BeanUtils.copyProperties(dto, user);
//            user.setRole(role);
            user = authUserRepository.save(user);
            dto.setId(user.getId());

        } else { // update
            Optional<AuthUser> userOpt = authUserRepository.findByIdAndDeletedFalse(dto.getId());
            if (!userOpt.isPresent())
                throw new DataNotFoundException("No se encontro el user con id: " + dto.getId());

            user = userOpt.get();
            user.setName(dto.getName());
            user.setUsername(dto.getUsername());
            user.setPassword(dto.getPassword());
            //user.setBirthDay(dto.getBirthDay());
//            user.setRole(role);
            authUserRepository.save(user);
        }

        if (dto.getRoles() == null)
            return dto.getId();

        // ROL
        for (AuthRole role : dto.getRoles()) {
            if (role == null)
                continue;

            role = authRoleRepository.findById(role.getId())
                    .orElseThrow(() -> new DataNotFoundException("No se encontro el rol a asignar"));
            AuthRoleUser roleUser = AuthRoleUser.builder().authUser(user).authRol(role).build();
            authRoleUserRepository.save(roleUser);
            log.info("se guardo para el usuario: {}, el rol: {}", user.getName(), role.getName());
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
