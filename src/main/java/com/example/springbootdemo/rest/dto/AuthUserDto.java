package com.example.springbootdemo.rest.dto;

import com.example.springbootdemo.enums.EntityState;
import com.example.springbootdemo.model.AuthRole;
import com.example.springbootdemo.model.AuthUser;
import com.example.springbootdemo.utils.IGetID;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * AuthUserDto
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDto implements IGetID, Serializable {

    private Long id;

    private String name;

    private String lastname;

    private String username;

    private String password;

    private Date birthDay;

    private boolean generatedPassword;

    private EntityState userStatus;

    private List<AuthRole> roles;

    public AuthUserDto(AuthUser authUser) {
        BeanUtils.copyProperties(authUser, this);
    }

}
