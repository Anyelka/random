package com.random.problems.leetCode.countingBits

import com.random.util.test

fun main() {
    val input = listOf(
        2 to intArrayOf(0,1,1),
        5 to intArrayOf(0,1,1,2,1,2)
    )

    input.forEach {
        val n = it.first
        it.test { Solution1().countBits(n) }
    }
}