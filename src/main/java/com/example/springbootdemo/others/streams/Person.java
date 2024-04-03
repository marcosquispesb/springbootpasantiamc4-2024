package com.example.springbootdemo.others.streams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    private BigDecimal salary;

    private List<Travel> travels = new ArrayList<>();

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, Integer age, BigDecimal salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
