package com.random.problems.other.wincent;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayMultiplyElements {
    //*
    // given an array of ints, specify the count of i<j<k<l index combinations where the first 3 elements multiply to the
    //    fourth element
    //
    // */
    public static void main(String[] args) {
        List<List<Integer>> inputs = List.of(
                List.of(1, 2, 3, 6, 6, 6),                                           // 1 + 1 + 1 = 3
                List.of(1, 1, 1, 1, 1, 1),                                           // 1 + 4 + 10 = 15
                List.of(1, 2, 3, 6, 2, 6),                                           // 1 + 3 = 4
                List.of(0, 1),                                                       // 0
                List.of(1, 1, 1, 1, 1),                                              // 1 + 4 = 5
                List.of(1, 1, 1, 1),                                                 // 4
                List.of(1,1,1),                                                      // 0
                List.of(1, 1, 1, 1, 1, 1, 1)                                         // 1 + 4 + 10 + 20 = 35
        );
        for(List<Integer> input: inputs) {
            int result = countV2(input.toArray(Integer[]::new));
            System.out.println(result);
        }

    }

    // 1. implementation: brute force: O(n^4) complexity
    private static int countV1(Integer[] array) {
        int count = 0;
        for(int i = 3; i < array.length; i++) {
            count += countV1(array, i);
        }
        return count;
    }

    private static int countV1(Integer[] array, int index) {
        int count = 0;
        for(int i = 0; i < index; i++) {
            for(int j = 0; j < i; j++) {
                for(int k = 0; k < j; k++) {
                    if(array[i] * array[j] * array[k] == array[index]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }


    // 2. implementation: cache with hashmap: O(n^3) complexity
    private static int countV2(Integer[] array) {
        int count = 0;

        for(int i = 3; i < array.length; i++) {
            count += countV2(array, i);
        }
        return count;
    }

    private static int countV2(Integer[] array, int index) {
        Map<Integer, Integer> frequencyMap = getFrequencyMap(array, index);
        Map<Integer, Set<Integer>> tripletMap = new HashMap<>();

        int count = 0;
        int target = array[index];
        for(int i = 0; i < index; i++) {
            for(int j = 0; j < i; j++) {
                int product = array[i] * array[j];
                int difference = target / product;

                if(frequencyMap.containsKey(difference)) {
                    //tripletMap.put(target, List.of(i, j, difference));


                    frequencyMap.get(difference);
                }
            }
        }
        return count;
    }

    @NotNull
    private static Map<Integer, Integer> getFrequencyMap(Integer[] array, int index) {
        Map<Integer, Integer> frequencyMap = new HashMap<>(array.length);
        for(int i = 0; i < index; i++) {
            Integer number = array[i];
            Integer frequency = frequencyMap.get(number);
            if(frequencyMap.containsKey(frequency)) {
                frequencyMap.put(number, frequency+1);
            } else {
                frequencyMap.put(number, 1);
            }
        }
        return frequencyMap;
    }


}
