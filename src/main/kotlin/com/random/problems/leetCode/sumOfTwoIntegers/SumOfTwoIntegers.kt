package com.random.problems.leetCode.sumOfTwoIntegers

import com.random.util.test

fun main() {
    val input = listOf(
        (1 to 2) to 3,
        (2 to 3) to 5,
        (-8 to 15) to 7,
        (500 to 319) to 819
    )

    input.forEach {
        val (a,b) = it.first
        it.test { Solution2().getSum(a,b) }
    }

}