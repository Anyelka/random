package com.random.problems.leetCode.mergeIntervals

import com.random.util.*

fun main() {
    val input = listOf(
        arrayOf(intArrayOf(1,3),intArrayOf(2,6),intArrayOf(8,10),intArrayOf(15,18)) to arrayOf(intArrayOf(1,6),intArrayOf(8,10),intArrayOf(15,18)),
        arrayOf(intArrayOf(1,4),intArrayOf(4,5)) to arrayOf(intArrayOf(1,5)),
        arrayOf(intArrayOf(1,4),intArrayOf(2,3)) to arrayOf(intArrayOf(1,4)),
        //arrayOf() to arrayOf(),
    )

    input.forEach {
        val intervals = it.first
        it.testWithPrint { Solution1().merge(intervals) }
    }
}

private fun Pair<Array<IntArray>, Array<IntArray>>.testWithPrint(method: (Array<IntArray>) -> Array<IntArray>) {
    val result = method(first)
    println("Result for ${formatArrayOfIntArrays(first)} is: ${formatArrayOfIntArrays(result)} - ${isCorrectStringWithExpectedArrayOfIntArrays(result, second)}")
}
