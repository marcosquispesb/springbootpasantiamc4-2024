package com.example.springbootdemo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * AddressDto
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String avenue;
    private String street;
    private Integer nro;
}
