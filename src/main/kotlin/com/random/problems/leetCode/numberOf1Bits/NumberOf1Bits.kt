package com.random.problems.leetCode.numberOf1Bits

import com.random.util.test

fun main() {
    val input = listOf(
        11 to 3,
        128 to 1,
        2147483645 to 30,
        8 to 1
    )

    input.forEach {
        val n = it.first
        it.test { Solution1().hammingWeight(n) }
    }

}