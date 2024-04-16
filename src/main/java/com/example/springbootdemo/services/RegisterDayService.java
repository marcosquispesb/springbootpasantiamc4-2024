package com.example.springbootdemo.services;

import com.example.springbootdemo.model.AuthUser;
import com.example.springbootdemo.model.RegisterDay;
import com.example.springbootdemo.rest.dto.AuthUserDto;
import com.example.springbootdemo.rest.dto.RegisterDayDto;

import java.util.Date;
import java.util.List;

/**
 * AuthUserService
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface RegisterDayService {

    List<RegisterDay> findAll();

    List<RegisterDay> findAllByUserId(Long userId);

    Long save(RegisterDay dto);

    void save(List<RegisterDayDto> registerDayDtos);
}
