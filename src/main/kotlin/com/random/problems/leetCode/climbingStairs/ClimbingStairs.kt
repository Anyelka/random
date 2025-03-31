package com.random.problems.leetCode.climbingStairs

import com.random.util.test

fun main() {
    val input: List<Pair<Int, Int>> = listOf(
        2 to 2,
        3 to 3,
        4 to 5,
        45 to 1836311903
    )

    input.forEach {
        val n = it.first
        it.test { Solution2().climbStairs(n) }
    }
}