package com.random.algorithms.sorts.testdata.generate;

import java.util.Random;

public class ArrayGenerator {
    public static int[] generateIntArray(int size) {
        int[] result = new int[size];
        Random random = new Random();
        for(int i = 0; i < size; i++) {
            result[i] = random.nextInt();
        }
        return result;
    }
}
