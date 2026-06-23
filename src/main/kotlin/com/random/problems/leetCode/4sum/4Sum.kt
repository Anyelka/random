package com.random.problems.leetCode.`4sum`

import com.random.util.testWithSet

fun main() {
    val input = listOf(
        (intArrayOf(1,0,-1,0,-2,2) to 0) to listOf(listOf(-1,0,0,1), listOf(-2,-1,1,2), listOf(-2,0,0,2)),
        (intArrayOf(2,2,2,2,2) to 8) to listOf(listOf(2,2,2,2)),
        (intArrayOf(1,0,1,0,-2,2) to 0) to listOf(listOf(-2,0,1,1),listOf(-2,0,0,2)),
        (intArrayOf(-2,2,-1,1,0,0) to 0) to listOf(listOf(-2,-1,1,2),listOf(-2,0,0,2),listOf(-1,0,0,1)),
        (intArrayOf(-2,-2,2,-1,1,1,0,0) to 0) to listOf(listOf(-2,-1,1,2),listOf(-2,0,0,2),listOf(-2,0,1,1),listOf(-1,0,0,1)),
        (intArrayOf(-2,-2,2,-1,1,1,0,0) to 0) to listOf(listOf(-2,-1,1,2),listOf(-2,0,0,2),listOf(-2,0,1,1),listOf(-1,0,0,1)),
        (intArrayOf(1000000000,1000000000,1000000000,1000000000) to -294967296) to emptyList<List<Int>>(),
        LongInput.INPUT
    )

    input.forEach {
        val (nums, target) = it.first
        it.testWithSet { Solution1().fourSum(nums, target) }
    }

}