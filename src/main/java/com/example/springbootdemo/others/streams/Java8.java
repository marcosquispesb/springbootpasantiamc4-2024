package com.example.springbootdemo.others.streams;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java8
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class Java8 {

    // obtener las personas mayores a 20 años y ordenarlos por nombre
    public void ageGreatest20(List<Person> personList) {
        personList.stream()
                .filter(x -> x.getAge() > 20)
                .sorted(Comparator.comparing(Person::getName))
                .forEach(System.out::println);
        //System.out.println(personList);
    }

    // obtener las edades unicos
    public void uniqueAges(List<Person> personList) {
        personList.stream()
                .map(Person::getAge)
                .distinct()
                .forEach(System.out::println);
    }

    // obtener la suma de las edades
    public void sumAges(List<Person> personList) {
        int sum = personList.stream()
                .map(Person::getAge)
                .mapToInt(x -> x)
                .sum();
        System.out.println(sum);
    }

    // obtener el sueldo promedio de las personas
    public void averageSalary(List<Person> personList) {
        BigDecimal sum = personList.stream()
                .map(Person::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sum);
        BigDecimal average = sum.divide(new BigDecimal(personList.size()), 1, RoundingMode.HALF_UP);
        System.out.println(average);
    }

    // obtener las ciudades unicas de viajes
    public void uniqueCountries(List<Person> personList) {
        personList.stream()
                .map(Person::getTravels)
                .flatMap(x -> x.stream())
                .map(Travel::getCountry)
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    // validar si un usuario viajo a un determinado pais
    public void validPersonTravelTo(List<Person> personList, String personaName, String countryTravel) {
        boolean existe = personList.stream()
                .filter(x -> x.getName().equalsIgnoreCase(personaName))
                .map(Person::getTravels)
                .flatMap(x -> x.stream())
                .map(Travel::getCountry)
                .anyMatch(x -> x.equalsIgnoreCase(countryTravel));
        System.out.println(existe);
    }

    public static void main(String[] args) {
        List<Person> persons;
        persons = Arrays.asList(
                new Person("Atila", 20, new BigDecimal("10.22"), Arrays.asList(new Travel("Francia"), new Travel("España")))
                , new Person("Aquiles", 25, new BigDecimal("5"))
                , new Person("Tony", 23, new BigDecimal("5.38"), Arrays.asList(new Travel("EEUU")))
                , new Person("Scarlet", 20, new BigDecimal(10))
                , new Person("Homero", 20, new BigDecimal("6.22"), Arrays.asList(new Travel("Tailandia"), new Travel("Japon"), new Travel("China")))
                , new Person("Peter", 23, new BigDecimal("4.28"))
                , new Person("Bruce", 15, new BigDecimal("20.5"), Arrays.asList(new Travel("Tailandia")))
                , new Person("Clark", 19, new BigDecimal("30.5"), Arrays.asList(new Travel("Kenia")))
        );

        Java8 java8 = new Java8();
//        java8.ageGreatest20(persons);
//        java8.uniqueAges(persons);
//        java8.sumAges(persons);
//        java8.averageSalary(persons);
//        java8.uniqueCountries(persons);
        java8.validPersonTravelTo(persons, "Bruce2", "Tailandia");
    }
}
