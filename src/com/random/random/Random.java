package com.random.random;

public class Random {

    public static void main(String[] args) {
        int[] intArray = { 20, 35, -15, 7, -22, 1, 55 };
        insertionSort2(intArray);
        printArray(intArray);
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

    // 2. Insertion sort
    //      2.a: with swap
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
    //      2.b: with temp element
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

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void printArray(int[] intArray) {
        for(int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
    }
}
