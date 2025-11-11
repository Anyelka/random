package com.random.problems.leetCode.onesAndZeroes

import com.random.util.test

fun main() {
    val input = listOf(
        Triple(arrayOf("10","0001","111001","1","0"),5, 3) to 4,
        Triple(arrayOf("10","0","1"), 1, 1) to 2,
        Triple(arrayOf("11","11","0","0","10","1","1","0","11","1","0","111","11111000","0","11","000","1","1","0","00","1","101","001","000","0","00","0011","0","10000"),
            90, 66) to 29
    )

    input.forEach {
        val (strs, m, n) = it.first
        it.test { Solution1().findMaxForm(strs, m , n) }
    }
}