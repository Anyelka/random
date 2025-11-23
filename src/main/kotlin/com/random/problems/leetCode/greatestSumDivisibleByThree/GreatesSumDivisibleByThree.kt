package com.random.problems.leetCode.greatestSumDivisibleByThree

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(3,6,5,1,8) to 18,
        intArrayOf(4) to 0,
        intArrayOf(1,2,3,4,4) to 12,
        intArrayOf(9,11,3) to 12,
        intArrayOf(10,8,1,4) to 18,
        //intArrayOf(21,634,3,25,324,6,3,41,2,6423,3,2,432,642,32,1234,5341,312,12,432,1321,321,6543,2,6,432,123,7542,423,1654) to 34287
    )

    input.forEach {
        val nums = it.first
        it.test { Solution1().maxSumDivThree(nums) }
    }
}