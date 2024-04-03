package com.example.springbootdemo.rest.dto;
import lombok.*;
import java.util.Date;
/**
 * UserDto
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name;
    private String lastName;
    private Date birthDay;
    private Integer age;
    private AddressDto address;
}
