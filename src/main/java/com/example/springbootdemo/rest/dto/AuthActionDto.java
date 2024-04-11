package com.example.springbootdemo.rest.dto;

import com.example.springbootdemo.model.AuthAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * AuthActionDto
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthActionDto implements Serializable {

    private Long id;

    private String code;

    private String action;

    public AuthActionDto(AuthAction action) {
        BeanUtils.copyProperties(action, this);
    }
}
