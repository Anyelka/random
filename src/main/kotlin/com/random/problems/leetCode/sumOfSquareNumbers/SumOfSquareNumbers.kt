package com.random.problems.leetCode.sumOfSquareNumbers

fun main() {
    val input = listOf(
        5 to true,
        3 to false,
        4 to true,
        2 to true,
        1 to true,
        0 to true,
        1833317 to true,
        1000000000 to true,
        2147483646 to false,
        2147483600 to true
    )
    input.forEach { it.test() }
}

private fun  Pair<Int, Boolean>.test() {
    val result = Solution2().judgeSquareSum(first)
    println("Result for $first: $result - ${isCorrect(second, result)}" )
}

fun isCorrect(expected: Boolean, actual: Boolean) = if(expected == actual) "Correct" else "WRONG SOLUTION !!!!!"
