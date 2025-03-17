package com.random.problems.hackerrank.biggerIsGreater

import com.random.util.getResourceAsText

const val FILE_PATH = "/hackerrank/biggerIsGreater/TestCase"

fun main() {
    val testCases = intArrayOf(0, 1, 4)
    testCases.forEach { it.test() }
}

private fun Int.test() {
    println("TestCase$this:")
    val inputLines = getResourceAsText("${FILE_PATH}${this}_in")!!.lines().drop(1)
    val outputLines = getResourceAsText("${FILE_PATH}${this}_out")!!.lines()

    val inputData = inputLines.withIndex().map { (i, line) -> line to outputLines[i] }
    // val inputData = listOf("fedcbabcd" to "fedcbabdc")
    inputData.forEach { (word, expectedResult) ->
        val result = Solution2().biggerIsGreater(word)
        println("Result for $word: $result - ${correct(result, expectedResult)}")
    }
}

fun correct(result: String, expected: String): String = if (result == expected) "Correct" else "WRONG SOLUTION !!!"
