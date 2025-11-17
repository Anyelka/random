package com.random.problems.leetCode.numberOfSubstringsWithOnlyOnes

import com.random.util.test

fun main() {
    val input = listOf(
        "0110111" to 9,
        "101" to 2,
        "111111" to 21,
        "1" to 1,
        "0" to 0,
        "11" to 3,
        "111" to 6,
        "1111" to 10,
        "11111" to 15,
        getBigInput()
    )

    input.forEach {
        val s = it.first
        it.test { Solution1().numSub(s) }
    }
}