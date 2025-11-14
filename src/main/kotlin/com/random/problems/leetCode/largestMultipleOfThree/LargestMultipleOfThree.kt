package com.random.problems.leetCode.largestMultipleOfThree

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(8,1,9) to "981",
        intArrayOf(8,6,7,1,0) to "8760",
        intArrayOf(1) to "",
        intArrayOf(8,6,7,5,3,4) to "876543",
        intArrayOf(8,6,7,5,4,4) to "87654",
        intArrayOf(8,1,4) to "84",
        intArrayOf(8,1,6,4) to "864",
        intArrayOf(4,3,1,8,7) to "7431",
        intArrayOf(0,0,0,0,0,0) to "0",
        intArrayOf(8,8,8) to "888",
        intArrayOf(8,8,7,7,7) to "8877",
        intArrayOf(8,7,7,7) to "777",
        intArrayOf(8,8,7) to "87",
        intArrayOf(9,8,6,8,6) to "966",
        intArrayOf(0,0,0,0,0,1) to "0",
        getInput18()
    )

    input.forEach {
        val digits = it.first
        it.test { Solution1().largestMultipleOfThree(digits) }
    }

}