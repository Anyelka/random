package com.random.problems.leetCode.medianOfTwoSortedArrays

import com.random.util.isCorrectString

fun main() {
    val input = listOf(
        (intArrayOf(1,3) to intArrayOf(2)) to 2.0,
        (intArrayOf(1,2) to intArrayOf(3,4)) to 2.5,
        (intArrayOf(1,2,3,7) to intArrayOf(4,5,6)) to 4.0,
        (intArrayOf(0, 1, 7, 9, 11, 15) to intArrayOf(7, 10, 103)) to 9.0,
        (intArrayOf(-1000000, 1000000) to intArrayOf(-1000000, 1000000)) to 0.0
    )

    input.forEach { it.test() }
}

private fun Pair<Pair<IntArray, IntArray>, Double>.test() {
    val (nums1, nums2) = first
    val result = Solution1().findMedianSortedArrays(nums1, nums2)
    println("Result for arrays: ${nums1.contentToString()} and ${nums2.contentToString()} is: $result - ${isCorrectString(result, second)}" )
}
