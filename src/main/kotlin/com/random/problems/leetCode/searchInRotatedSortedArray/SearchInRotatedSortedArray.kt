package com.random.problems.leetCode.searchInRotatedSortedArray

import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(4,5,6,7,0,1,2) to 0) to 4,
        (intArrayOf(4,5,6,7,0,1,2) to 3) to -1,
        (intArrayOf(1) to 0) to -1,
        (intArrayOf(3,1) to 1) to 1,
        (intArrayOf(3,5,1) to 5) to 1
    )

    input.forEach {
        val (nums, target) = it.first
        it.test { Solution2().search(nums, target) }
    }

}