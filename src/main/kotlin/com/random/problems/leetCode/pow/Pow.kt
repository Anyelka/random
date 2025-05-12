package com.random.problems.leetCode.pow

import com.random.problems.adventOfCode.twentyFour.util.runAndLogTime
import com.random.util.test
import kotlin.time.measureTime

fun main() {
    val input = listOf(
        (2.0 to 10) to 1024.0,
        (2.1 to 3) to 9.261000000000001,
        (2.0 to -2) to 0.25000,
        (2.0 to -200000000) to 0.00000,
        (0.00001 to 2147483647) to 0.0,
        (1.00000 to -2147483648) to 1.0
    )

    input.forEach {
        val (x, n) = it.first
        runAndLogTime{ it.test { Solution1().myPow(x,n) } }
    }
}