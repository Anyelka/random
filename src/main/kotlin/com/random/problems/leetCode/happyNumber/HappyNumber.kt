package com.random.problems.leetCode.happyNumber

import com.random.util.test

fun main() {
    val input = listOf(
        10 to true,
        2 to false,
        7 to true
    )

    input.forEach {
        val n = it.first
        it.test { Solution().isHappy(n) }
    }
}