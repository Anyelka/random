package com.random.problems.leetCode.bestTimeToBuyAndSellStock

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(7,1,5,3,6,4) to 5,
        intArrayOf(7,6,4,3,1) to 0,
        TestInput1.getInput()
    )

    input.forEach {
        val nums = it.first
        it.test { Solution1().maxProfit(nums) }
    }
}