package com.random.algorithms.sorts.java;

import com.random.algorithms.sorts.testdata.generate.ArrayGenerator;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SortingAlgorithms {

    public static void main(String[] args) throws IOException {

        /*Map<int[], int[]> inputData = InputData.get100kLength();
        Map<String, Consumer<int[]>> sortingAlgorithms = Map.of(
                "Bubble sort", SortingAlgorithms::bubbleSort,
                "Selection sort", SortingAlgorithms::selectionSort,
                "Insertion sort v1: with swaps", SortingAlgorithms::insertionSort,
                "Insertion sort v2: with shifting", SortingAlgorithms::insertionSort2,
                "Shell sort", SortingAlgorithms::shellSort,
                "Merge sort", SortingAlgorithms::mergeSort
        );

        inputData.forEach((input, expectedOutput) -> test(input, expectedOutput, sortingAlgorithms));*/

        generateRandomArrayAndWriteInputOutput(1000000, "TestInput1M", "TestOutput1M");
    }

    private static void generateRandomArrayAndWriteInputOutput(int arraySize, String inputFile, String outputFile) {
        // Write the 1. Input array and the 2. Output array to files:
        //      1. generate random array of size:
        int[] randomArray = ArrayGenerator.generateIntArray(arraySize);
        Consumer<int[]> sortingMethod = SortingAlgorithms::mergeSort;
        try {
            //      2. Sort with write
            FileWriter.runWithInputOutputWrite(inputFile, outputFile, randomArray, sortingMethod);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void test(int[] array, int[] expectedSortedArray, Map<String, Consumer<int[]>> sortingAlgorithms) {
        System.out.println("Array: " + shortFormatArray(array));
        sortingAlgorithms.forEach((name, algorithm) -> {
            System.out.print("        - Sorting with " + name + "...");
            Double timeElapsed = runAndMeasureTime(() -> algorithm.accept(array));
            if(!Arrays.equals(expectedSortedArray, array)) {
                System.err.println("            -> Wrong result: " + shortFormatArray(array));
            }
            System.out.print("      - Time taken: " + timeElapsed + "s\n");
        });
    }

    private static Double runAndMeasureTime(Runnable method) {
        long start = System.nanoTime();
        method.run();
        long finish = System.nanoTime();
        return (double) (finish - start) / 1000000000;
    }

    private static void sort(int[] array) {
        bubbleSort(array);
    }


    // 1. Bubble sort
    private static void bubbleSort(int[] array) {
        for(int lastUnsortedIndex = array.length - 1; lastUnsortedIndex >= 0; lastUnsortedIndex--) {
            for(int i = 0; i < lastUnsortedIndex; i++) {
                if(array[i] > array[i+1]) {
                    swap(array, i, i+1);
                }
            }
        }
    }

    // 2. Selection sort
    private static void selectionSort(int[] array) {
        for(int lastUnsortedIndex = array.length - 1; lastUnsortedIndex >= 0; lastUnsortedIndex--) {
            int biggestIndex = 0;
            for(int i = 0; i <= lastUnsortedIndex; i++) {
                if(array[i] > array[biggestIndex]) {
                    biggestIndex = i;
                }
            }
            swap(array, biggestIndex, lastUnsortedIndex);
        }
    }

    // 3. Insertion sort
    //      3.a: with swap
    private static void insertionSort(int[] array) {
        for(int firstUnsortedIndex = 1; firstUnsortedIndex < array.length  - 1; firstUnsortedIndex++) {
            for(int i = firstUnsortedIndex - 1; i >= 0; i--) {
                if(array[i] > array[i+1]) {
                    swap(array, i, i+1);
                } else {
                    break;
                }
            }
        }
    }
    //      3.b: with temp element
    private static void insertionSort2(int[] array) {
        for(int firstUnsortedIndex = 1; firstUnsortedIndex < array.length; firstUnsortedIndex++) {
            int nextElement = array[firstUnsortedIndex];
            int i;
            for(i = firstUnsortedIndex; i > 0; i--) {
                if(array[i-1] > nextElement) {
                    array[i] = array[i-1];
                } else {
                    break;
                }
            }
            array[i] = nextElement;
        }
    }

    // 4. Shell sort
    private static void shellSort(int[] array) {
        int gap = array.length / 2;
        while(gap > 0) {
            for(int firstUnsortedIndex = gap; firstUnsortedIndex < array.length; firstUnsortedIndex++) {
                int nextElement = array[firstUnsortedIndex];

                int j;
                for(j = firstUnsortedIndex; j >= gap; j-=gap) {
                    int element = array[j - gap];
                    if(element > nextElement) {
                        array[j] = element;
                    } else {
                        break;
                    }
                }
                array[j] = nextElement;

            }
            gap = gap / 2;
        }
    }

    private static void mergeSort(int[] array) {
        int size = array.length;
        if(size <= 1) {
            return;
        }
        int midIndex = size / 2;
        int[] firstHalf = new int[midIndex];
        int[] secondHalf = new int[size - midIndex];
        System.arraycopy(array, 0, firstHalf, 0, midIndex);
        if (size - midIndex >= 0) System.arraycopy(array, midIndex, secondHalf, 0, size - midIndex);

        mergeSort(firstHalf);
        mergeSort(secondHalf);

        merge(array, firstHalf, secondHalf);
    }

    private static void merge(int[] array, int[] firstHalf, int[] secondHalf) {
        int i = 0, j = 0, k = 0;
        while(i < firstHalf.length && j < secondHalf.length) {
            int first = firstHalf[i];
            int second = secondHalf[j];
            if(first < second) {
                array[k] = first;
                i++;
            } else {
                array[k] = second;
                j++;
            }
            k++;
        }

        while(i < firstHalf.length) {
            array[k] = firstHalf[i];
            i++;
            k++;
        }

        while(j < secondHalf.length) {
            array[k] = secondHalf[j];
            j++;
            k++;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static String shortFormatArray(int[] intArray) {
        return "[" + intArray[0] + "," + intArray[1] + "," + intArray[2] + "," + intArray[3] +  "," + intArray[4] +
                "... ] (" + intArray.length + " elements)";
    }

    private static String formatArray(int[] intArray) {
        return Arrays.stream(intArray).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }
}

