package com.random.problems.leetCode.courseSchedule2

import com.random.util.*

fun main() {
    val input = listOf(
        (arrayOf(intArrayOf(1, 0)) to 2) to arrayOf(intArrayOf(0, 1)),
        (arrayOf(intArrayOf(1,0),intArrayOf(2,0),intArrayOf(3,1),intArrayOf(3,2)) to 4) to arrayOf(intArrayOf(0,1,2,3), intArrayOf(0,2,1,3)),
        (arrayOf<IntArray>() to 1) to arrayOf(intArrayOf(0)),
        (arrayOf(intArrayOf(0,1), intArrayOf(1,0)) to 2) to arrayOf(intArrayOf()),
        (arrayOf(intArrayOf(1,0), intArrayOf(1,2), intArrayOf(0,1)) to 3) to arrayOf(intArrayOf()),
        getTestInput()
    )

    input.forEach {
        val (prerequisites, numCourses) = it.first
        it.testIt { Solution1().findOrder(numCourses, prerequisites) }
    }
}

private fun Pair<Pair<Array<IntArray>, Int>, Array<IntArray>>.testIt(method: (Pair<Array<IntArray>, Int>) -> IntArray) {
    val result = method(first)
    val inputString = "${formatArrayOfIntArrays(first.first)} - ${first.second}"
    val resultString = shortFormatArrayIfNeeded(result)
    val isCorrectString = isCorrectStringWithExpectedFromArrayOfIntArrays(result, second)
    println("Result for ($inputString ) is: $resultString - $isCorrectString")
}

