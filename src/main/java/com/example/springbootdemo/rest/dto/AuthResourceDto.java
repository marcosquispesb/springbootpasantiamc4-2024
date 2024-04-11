package com.example.springbootdemo.rest.dto;

import com.example.springbootdemo.model.AuthResource;
import com.example.springbootdemo.model.AuthRoleResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * AuthResourceDto
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResourceDto implements Serializable {

    private Long id;

    private String name;

    private String url;

    private Integer menuPosition;

    private List<AuthResourceDto> children;

    private List<AuthActionDto> actions;

    public AuthResourceDto(AuthResource authResource) {
        BeanUtils.copyProperties(authResource, this);
    }

    public AuthResourceDto(AuthRoleResource authRoleResource) {
        BeanUtils.copyProperties(authRoleResource.getAuthResource(), this);
    }
}
