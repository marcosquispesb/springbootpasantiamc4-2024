package com.example.springbootdemo.others;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Person {

    private String name;

    private Integer age;

}
