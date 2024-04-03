package com.example.springbootdemo.others.streams;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Java8
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class Java8 {

    // obtener las personas mayores a 20 años
    private void ageGreatest20(List<Person> personList) {

    }

    // obtener las edades unicos
    private void uniqueAges(List<Person> personList) {

    }

    // obtener la suma de los salarios
    private void sumSalary(List<Person> personList) {

    }

    // obtener el sueldo promedio de las personas
    private void averageSalary(List<Person> personList) {

    }

    // obtener las ciudades unicas de viajes
    private void uniqueCountries(List<Person> personList) {

    }

    public static void main(String[] args) {
        List<Person> persons;
        persons = Arrays.asList(
                new Person("Atila", 20, new BigDecimal("10.22"), Arrays.asList(new Travel("Francia"), new Travel("España")))
                , new Person("Aquiles", 25, new BigDecimal("5"))
                , new Person("Tony", 23, new BigDecimal(5.38d), Arrays.asList(new Travel("EEUU")))
                , new Person("Scarlet", 20, new BigDecimal(10))
                , new Person("Homero", 20, new BigDecimal("6.22"), Arrays.asList(new Travel("Tailandia"), new Travel("Japon"), new Travel("China")))
                , new Person("Peter", 23, new BigDecimal("4.28"))
                , new Person("Bruce", 15, new BigDecimal("20.5"), Arrays.asList(new Travel("Tailandia")))
                , new Person("Clark", 19, new BigDecimal("30.5"), Arrays.asList(new Travel("Kenia")))
        );

        //List<Person> list2 = Arrays.asList(
        //                new Person("Atila", 20, Arrays.asList(new Travel("Francia"), new Travel("España")))
        //                , new Person("Aquiles", 25, Arrays.asList(new Travel("EEUU")))
        //                , new Person("Tony", 23, Arrays.asList(new Travel("Tailandia"), new Travel("Japon"), new Travel("China")))
        //                , new Person("Scarlet", 20)//, Arrays.asList(new Travel("Kenia")))
        //        );
    }
}
