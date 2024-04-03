package com.example.springbootdemo.others;

import com.example.springbootdemo.others.streams.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DataTypes
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class DataTypes {

    public static void aux(int nro) {
        nro++;
        System.out.println(nro);
    }

    public static void main(String[] args) {
        // primitivos: no tienen metodos, no aceptan null
        // boolean, int, long, double, char
        int n1 = 7;
        System.out.println(n1);
        boolean state = true;
        System.out.println(state);

        // Wrappers: tienen metodos, aceptan valores nulos, sus clases tienen constantes
        // Boolean, Integer, Long, Double, Character
        Integer n2 = 7;
        System.out.println(n2);
        n2 = null;
        Boolean state2 = true;
        System.out.println(state2);

        n2 = 6;
        aux(n2);
        n2 = null;
        //aux(n2); // da error

        // ------------------------------------------------------
        // String: es inmutable, tienen metodos, aceptan valores nulos

        String s = "Hola mundo";
        s = null;
        s = "Hola mando";
        System.out.println(s);

        // ------------------------------------------------------
        // array: se usa new, acepta primitivos|wrappers|Otras Clases.

        int[] array0 = new int[5];
        array0[0] = 1;
        System.out.println(array0);
        System.out.println(Arrays.toString(array0));

        //int[] arrayPrim = new int[]{1, 2, 3};
        int[] arrayPrim = {2, 4, 6, 8, 1};
        System.out.println(Arrays.toString(arrayPrim));

        Integer[] arrayWrap = new Integer[5];
        System.out.println(Arrays.toString(arrayWrap));

        boolean[] arrayBoolP = new boolean[5];
        System.out.println(Arrays.toString(arrayBoolP));

        Boolean[] arrayBoolW = new Boolean[5];
        System.out.println(Arrays.toString(arrayWrap));

        //Person person = new Person();
        Person[] arrayPersons = new Person[] {
                new Person("Omar", 17)
                , new Person("Ariana", 15)
        };
        System.out.println(Arrays.toString(arrayPersons));

        // ------------------------------------------------------
        // List: se usa new, acepta wrappers|Otras Clases.

        //List<int> list; // NO es posible
        //List<Arrays> list; // es posible

        // Arrays.asList crea una lista inmutable, es decir que tu tamaño no podra ser modificado
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5); // lista con tamaño fijo
//        list.remove(0); // NO es posible
//        list.add(0, 10); // NO es posible
        list.set(0, 100); // es posible
        System.out.println(list);

        list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)); // lista con tamaño dinamico
        list.remove(0); // es posible
        list.add(3, 10); // es posible
        list.set(0, 200); // es posible
        System.out.println(list);
    }
}
