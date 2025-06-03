package com.random.problems.leetCode.coinChange2

import com.random.util.test

fun main() {
    val input = listOf(
        (5 to intArrayOf(1, 2, 5)) to 4,
        (3 to intArrayOf(2)) to 0,
        (10 to intArrayOf(10)) to 1,
        (500 to intArrayOf(1, 2, 5)) to 12701
    )

    input.forEach {
        val (amount, coins) = it.first
        it.test { Solution1().change(amount, coins) }
    }

}