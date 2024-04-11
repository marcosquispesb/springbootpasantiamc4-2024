package com.example.springbootdemo.rest.dto;

import com.example.springbootdemo.enums.EntityState;
import com.example.springbootdemo.model.AuthRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * AuthRoleDto
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRoleDto implements Serializable {

    private Long id;

    private String name;

    private String description;

    private EntityState roleStatus;

    public AuthRoleDto(AuthRole authRole) {
        BeanUtils.copyProperties(authRole, this);
    }
}
