package com.example.springbootdemo.rest.api;

import com.example.springbootdemo.rest.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * UserResponse
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    boolean status;
    String message;
    String codeError;
    UserDto user;
}
