package com.example.springbootdemo.others;

import java.util.Arrays;

/**
 * Permutation
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class Permutation {

    public static void permutation(int[] values, int positionOriginal, Integer[] permutation) {
        for (int i = 0; i < values.length; i++) {
            if (permutation[i] == null) {
                permutation[i] = values[positionOriginal];
                permutation(values, positionOriginal + 1, permutation);

                if (positionOriginal == values.length - 1) { // permutacion lograda
                    System.out.println(Arrays.toString(permutation));
                    System.out.println("permutacion");
                }

                permutation[i] = null;
            }
        }
    }

    public static void main(String[] args) {
        int[] values = {10, 20, 30, 40, 50, 60};
        permutation(values, 0, new Integer[values.length]);
    }
}
