package com.random.algorithms.sorts;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class SortingAlgorithms {

    public static void main(String[] args) throws IOException {
        int[] intArray = { 20, 35, -15, 7, -22, 1, 55 };
        int[] intArray2 = { 25852, 18681, 34660, 83664, 4773, 29044, 41343, 73432, 27534, 69171, 35708, 4870, 60518, 82254, 37345 };
        int[] intArray3 = { 64, -30, 22, 49, 85, 95, 45, -15, 19, 134 };

        int[] randomArray = generateIntArray(100000);
        Files.write(Paths.get("TestInput100k"), formatArray(randomArray).getBytes(StandardCharsets.UTF_8));


        long start = System.nanoTime();
        bubbleSort(randomArray);
        long finish = System.nanoTime();

        printArray(randomArray);

        double elapsedTime = (double) (finish - start) / 1000000000;
        System.out.println("Elapsed time: " + elapsedTime + "s ");

        Files.write(Paths.get("TestOutput100k"), formatArray(randomArray).getBytes(StandardCharsets.UTF_8));
    }

    private static int[] generateIntArray(int size) {
        int[] result = new int[size];
        Random random = new Random();
        for(int i = 0; i < size; i++) {
            result[i] = random.nextInt();
        }
        return result;
    }

    private static String formatArray(int[] array) {
        return "[" + Arrays.toString(array) + "]";
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

    private static void printArray(int[] intArray) {
        for (int j : intArray) {
            System.out.print(j + ",");
        }
    }
}

