package com.random.problems.leetCode.jumpGame

import com.random.util.test

fun main() {
    val inputs = listOf(
        intArrayOf(2,3,1,1,4) to true,
        intArrayOf(3,2,1,0,4) to false,
        intArrayOf(0) to true,
        intArrayOf(0,2,3) to false,
        intArrayOf(1,2) to true,
        intArrayOf(8,2,4,4,4,9,5,2,5,8,8,0,8,6,9,1,1,6,3,5,1,2,6,6,0,4,8,6,0,3,2,8,7,6,5,1,7,0,3,4,8,3,5,9,0,4,0,1,0,5,9,2,0,7,0,2,1,0,8,2,5,1,2,3,9,7,4,7,0,0,1,8,5,6,7,5,1,9,9,3,5,0,7,5) to true,
        getBigInput()
    )


    inputs.forEach {
        val nums = it.first
        it.test { Solution2().canJump(nums) }
    }

}