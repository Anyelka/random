package com.random.problems.leetCode.countTheNumberOfSubstringsWithDominantOnes

import com.random.util.test

fun main() {
    val input = listOf(
        "00011" to 5,
        "101101" to 16,
        getLongInput1(),
        getLongInput2()
    )

    input.forEach {
        val s = it.first
        it.test { Solution1().numberOfSubstrings(s) }
    }
}