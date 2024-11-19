package com.random.algorithms.sorts.kotlin

import com.random.algorithms.sorts.testdata.TestDataProvider

fun main() {
    val algorithms = listOf(
            BubbleSort,
            SelectionSort,
            InsertionSort,
            ShellSort,
            MergeSort,
            QuickSort,
            CountingSort
    )

    // 1. Base case: basic test array of [20, 35, -15, 7, -22, 1, 55]
    //val testData = InputData.getBase()

    // 2. Some basic arrays:
    //val testData = InputData.getAll()

    // 3. A long array with 100 000 random integers
    // val testData = InputData.get100kLength()

    // 4. A very long array with 1 000 000 random integers
    // val testData = InputData.get1MLength()

    // 5. special data set for counting sort:
     val testData = TestDataProvider.getForCountingSort()

    testData.forEach{ it.toPair().test(algorithms) }
}

private fun Pair<IntArray, IntArray>.test(algorithms: List<SortingAlgorithm>) {
    println("Sorting array: ${shortFormatArray(first)}")
    algorithms.forEach { it.test(this) }
}

fun shortFormatArray(array: IntArray): String {
    return "[ ${array[0]}, ${array[1]}, ${array[2]}, ${array[3]}, ${array[4]}, ... ] (${array.size} elements)"
}

// Performance
// Performance for array of 100k random Ints:
//      - Bubble Sort:          5.413123541s
//      - Selection Sort:       2.519827541s
//      - Insertion Sort:       424.755667ms
//      - Shell Sort:           10.346458ms
//      - Merge Sort:           8.198541ms
//      - Quick Sort:           7.673292ms

// Performance for array of 1M random Ints
//      - Bubble Sort:          17m 9.578671292s
//      - Selection Sort:       4m 29.413012042s
//      - Insertion Sort:       44.630190458s
//      - Shell Sort:           123.196292ms
//      - Merge Sort:           80.327500ms
//      - Quick Sort:           64.113208ms
