package com.random.problems.leetCode.uniquePaths

import com.random.util.test

fun main() {
    val input = listOf<Pair<Pair<Int, Int>, Int>>(
        (3 to 7) to 28,
        (3 to 2) to 3,
        (23 to 12) to 193536720
    )

    input.forEach {
        val (m, n) = it.first
        it.test { Solution1().uniquePaths(m, n) }
    }
}