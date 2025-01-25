package com.random.algorithms;

public class Algorithms {

    public static void main(String[] args) {
        int[] intArray = { 20, 35, -15, 7, -22, 1, 55 };
        intArray = generateArray(10000000);
        long start = System.nanoTime();
        mergeSort(intArray);
        long finish = System.nanoTime();

        printArray(intArray);

        double elapsedTime = (double) (finish - start) / 1000000000;
        System.out.println("Elapsed time: " + elapsedTime + "s ");
    }

    private static int[] generateArray(int size) {
        int[] result = new int[size];
        for(int i = 0; i < size; i++) {
            result[i] = (int) (Math.random() * size);
        }
        return result;
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
        for(int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
    }
}
