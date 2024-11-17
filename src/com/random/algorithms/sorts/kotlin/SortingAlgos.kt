package com.random.algorithms.sorts.kotlin

import com.random.algorithms.sorts.testdata.InputData

fun main() {
    val algorithms = listOf(
            /*BubbleSort,
            SelectionSort,
            InsertionSort,
            ShellSort,*/
            MergeSort
    )

    // 1. Base case: basic test array of [20, 35, -15, 7, -22, 1, 55]
    // val testData = InputData.getBase()

    // 2. Some basic arrays:
    //val testData = InputData.getAll()

    // 3. A long array with 100 000 random integers
    val testData = InputData.get100kLength()

    testData.forEach{ it.toPair().test(algorithms) }
}

private fun Pair<IntArray, IntArray>.test(algorithms: List<SortingAlgorithm>) {
    println("Sorting array: ${shortFormatArray(first)}")
    algorithms.forEach { it.test(this) }
}

fun shortFormatArray(array: IntArray): String {
    return "[ ${array[0]}, ${array[1]}, ${array[2]}, ${array[3]}, ${array[4]}, ... ] (${array.size} elements)"
}

