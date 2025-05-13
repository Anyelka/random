package com.random.problems.leetCode.slidingWindowMaximum

import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(1,3,-1,-3,5,3,6,7) to 3) to intArrayOf(3,3,5,5,6,7),
        (intArrayOf(1) to 1) to intArrayOf(1),
        getBigInput()
    )


    input.forEach {
        val (nums, k) = it.first
        it.test {
            Solution1().maxSlidingWindow(nums, k)
        }
    }

}