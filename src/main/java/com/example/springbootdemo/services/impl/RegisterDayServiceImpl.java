package com.example.springbootdemo.services.impl;

import com.example.springbootdemo.model.AuthRole;
import com.example.springbootdemo.model.AuthUser;
import com.example.springbootdemo.model.RegisterDay;
import com.example.springbootdemo.repository.AuthRoleRepository;
import com.example.springbootdemo.repository.AuthUserRepository;
import com.example.springbootdemo.repository.RegisterDayRepository;
import com.example.springbootdemo.rest.dto.RegisterDayDto;
import com.example.springbootdemo.rest.exceptions.DataNotFoundException;
import com.example.springbootdemo.rest.exceptions.OperationException;
import com.example.springbootdemo.services.AuthRoleService;
import com.example.springbootdemo.services.RegisterDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * AuthRoleServiceImpl
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Service
public class RegisterDayServiceImpl implements RegisterDayService {

    @Autowired
    private RegisterDayRepository registerDayRepository;

    @Autowired
    private AuthUserRepository authUserRepository;

    @Override
    public List<RegisterDay> findAll() {
        return registerDayRepository.findAllByDeletedFalse();
    }

    @Override
    public List<RegisterDay> findAllByUserId(Long userId) {
        authUserRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new DataNotFoundException("No se encontro el usuario a asignar el horario"));

        return registerDayRepository.findAllByUser_Id(userId);
    }

    @Override
    public Long save(RegisterDay dto) {

        if (dto.getUser() == null)
            throw new OperationException("El dato user es requerido");

        System.out.println(dto.getUser().getId());
        AuthUser user = authUserRepository.findByIdAndDeletedFalse(dto.getUser().getId())
                .orElseThrow(() -> new DataNotFoundException("No se encontro el usuario a asignar el horario"));

        // validar que para un usuario no se registre mas de un ingreso o salida por dia
        RegisterDay entity = new RegisterDay();
        entity.setDateStart(dto.getDateStart());
        entity.setDateEnd(dto.getDateEnd());
        entity.setUser(user);
        entity = registerDayRepository.save(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public void save(List<RegisterDayDto> registerDayDtos) {
        // implementar

        // cada registro de la lista se debe guardar
//        AuthUser user = authUserRepository.findByUsername(userName);
//        if (user == null)
//            throw new DataNotFoundException("No se encontro el usuario a asignar el horario");
//
//        // validar que para un usuario no se registren repetidos
//        RegisterDay entity = new RegisterDay();
//        entity.setDateStart(dateStart);
//        entity.setDateEnd(dateEnd);
//        entity.setUser(user);
//        entity = registerDayRepository.save(entity);
//        return entity.getId();
    }
}
