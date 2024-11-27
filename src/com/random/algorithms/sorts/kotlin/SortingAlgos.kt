package com.random.algorithms.sorts.kotlin

import com.random.algorithms.sorts.testdata.TestDataProvider


fun main() {
    // 1. Challenge: descending merge sort
    /*Pair(intArrayOf(20, 35, -15, 7, -22, 1, 55), intArrayOf(55, 35, 20, 7, 1, -15, -22))
            .test(listOf(DescendingMergeSort))*/

    val algorithms = listOf(
            BubbleSort,
            SelectionSort,
            InsertionSort,
            ShellSort,
            DefaultMergeSort,
            QuickSort,
            // CountingSort(1, 10),
            // RadixSort(10, 10),
            KotlinSort
    )

    // 1. Base case: basic test array of [20, 35, -15, 7, -22, 1, 55]
    // val testData = TestDataProvider.getBase()

    // 2. Some basic arrays:
    val testData = TestDataProvider.getAll()

    // 3. A long array with 100 000 random integers
    // val testData = TestDataProvider.get100kLength()

    // 4. A very long array with 1 000 000 random integers
    // val testData = TestDataProvider.get1MLength()

    // 5. special data set for counting sort:
    // val testData = TestDataProvider.getForCountingSort()

    // 6. special data set for counting sort with 100k elements:
    // val testData = TestDataProvider.get100kForCountingSort()

    // 7. special data set for radix sort:
    // val testData = TestDataProvider.getForRadixSort()

    // 8. special data set for radix sort with 100k elements:
    // val testData = TestDataProvider.get100kForRadixSort()

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

// Performance for array of 100k Ints between 1 and 100 000:
//      - Bubble Sort:          4.999140291s
//      - Selection Sort:       2.664215667s
//      - Insertion Sort:       447.291917s
//      - Shell Sort:           10.545708ms
//      - Merge Sort:           8.192625ms
//      - Quick Sort:           8.191250ms
//      - Counting Sort:        1.709375

object KotlinSort : SortingAlgorithm() {
    override fun run(array: IntArray) {
        array.sort()
    }

    override fun getName(): String {
        return "Default kotlin sort algorithm"
    }

}