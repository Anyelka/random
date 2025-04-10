package com.random.problems.hackerrank.chocolateFeast

import com.random.util.isCorrectStringWithExpected
import com.random.util.shortFormatArrayIfNeeded

fun main() {
    val input = listOf(
        intArrayOf(15, 3, 2) to 9,
        intArrayOf(10, 2, 5) to 6,
        intArrayOf(12, 4, 4) to 3,
        intArrayOf(6, 2, 2) to 5
    )

    input.forEach { it.test() }
}

private fun Pair<IntArray, Int>.test() {
    val result = Solution1().chocolateFeast(first[0], first[1], first[2])
    println("Result for strings: ${shortFormatArrayIfNeeded(first.toTypedArray())} = $result - ${isCorrectStringWithExpected(result, second)}")
}