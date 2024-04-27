package com.example.springbootdemo.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * AtrasoPorUsuario
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Data
@Builder
public class AtrasoPorUsuario {
    Date date;
    long hrsFalta;
    long minutosAtraso;
}
