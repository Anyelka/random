package com.random.problems.hackerrank.chocolateFeast

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(15, 3, 2) to 9,
        intArrayOf(10, 2, 5) to 6,
        intArrayOf(12, 4, 4) to 3,
        intArrayOf(6, 2, 2) to 5
    )

    input.forEach {
        val input = it.first
        it.test { Solution2().chocolateFeast(input[0], input[1], input[2]) }
    }
}