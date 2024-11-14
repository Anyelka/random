package com.random.algorithms.sorts

fun main() {
    val algorithms = listOf(
            BubbleSort,
            SelectionSort,
            InsertionSort
    )

    val testData = mapOf(IntArray100k.pair())

    testData.forEach{ it.toPair().test(algorithms) }
}

private fun Pair<IntArray, IntArray>.test(algorithms: List<SortingAlgorithm>) {
    println("Sorting array: ${shortFormatArray(first)}")
    algorithms.forEach { it.test(this) }
}

fun shortFormatArray(array: IntArray): String {
    val i = array[0]
    val j = array[1]
    val k = array[2]
    val m = array[3]
    val n = array[4]

    return "[ $i, $j, $k, $m, $n, ... ] (${array.size} elements)"
}

