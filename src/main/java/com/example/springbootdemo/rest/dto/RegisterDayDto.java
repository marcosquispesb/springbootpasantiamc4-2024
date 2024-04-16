package com.example.springbootdemo.rest.dto;

import lombok.Data;

import java.util.Date;

/**
 * RegisterDayDto
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Data
public class RegisterDayDto {

    private String userName;

    private Date dateStart;

    private Date dateEnd;
}
