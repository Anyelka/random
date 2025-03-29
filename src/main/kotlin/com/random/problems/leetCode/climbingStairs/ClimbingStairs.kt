package com.random.problems.leetCode.climbingStairs

import com.random.util.test

fun main() {
    val input: List<Pair<Int, Int>> = listOf(
        2 to 2,
        3 to 3,
        4 to 5
    )

    input.forEach {
        val n = it.first
        it.test { Solution1().climbStairs(n) }
    }
}