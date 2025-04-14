package com.random.problems.leetCode.addTwoNumbers

import com.random.util.test

fun main() {
    val inputs = listOf(
        (intArrayOf(2,4,3) to intArrayOf(5,6,4)) to intArrayOf(7,0,8),
        (intArrayOf(0) to intArrayOf(0)) to intArrayOf(0),
        (intArrayOf(9,9,9,9,9,9,9) to intArrayOf(9,9,9,9)) to intArrayOf(8,9,9,9,0,0,0,1)
    )

    inputs.forEach {
        val (nums1, nums2) = it.first
        it.test { Solution1().convertAndAdd(nums1, nums2) }
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}