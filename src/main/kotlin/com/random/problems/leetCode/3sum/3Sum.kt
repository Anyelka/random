package com.random.problems.leetCode.`3sum`

import com.random.util.test
import com.random.util.testWithSet

fun main() {
    val input = listOf(
        (intArrayOf(-1,0,1,2,-1,-4) to listOf(listOf(-1,-1,2), listOf(-1,0,1))),
        (intArrayOf(0,1,1) to emptyList<List<Int>>()),
        (intArrayOf(0,0,0) to listOf(listOf(0,0,0))),
        (intArrayOf(-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0) to listOf(listOf(-5,1,4),listOf(-4,0,4),listOf(-4,1,3),listOf(-2,-2,4),listOf(-2,1,1),listOf(0,0,0))),
        (intArrayOf(1,2,-2,-1) to emptyList<List<Int>>())
        /*LongInput.INPUT*/
    )

    input.forEach {
        val nums = it.first
        it.testWithSet { Solution2().threeSum(nums) }
    }

}