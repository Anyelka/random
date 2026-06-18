package com.random.problems.leetCode.angleBetweenHandsOfClock

import com.random.util.test

fun main() {
    val input = listOf(
        (12 to 30) to 165.0,
        (3 to 30) to 75.0,
        (3 to 15) to 7.5
    )

    input.forEach {
        val (h,m) = it.first
        it.test { Solution1().angleClock(h,m) }
    }
}