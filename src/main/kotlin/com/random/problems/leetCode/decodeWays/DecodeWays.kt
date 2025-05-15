package com.random.problems.leetCode.decodeWays

import com.random.util.test

fun main() {
    val input = listOf(
        "12" to 2,
        "226" to 3,
        "06" to 0,
        "11106" to 2,
        "111111111111111111111111111111111111111111111" to 1836311903
    )

    input.forEach {
        val s = it.first
        it.test { Solution1().numDecodings(s) }
    }

}