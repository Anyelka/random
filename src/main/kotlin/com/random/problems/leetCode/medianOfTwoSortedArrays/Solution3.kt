package com.random.problems.leetCode.medianOfTwoSortedArrays

class Solution3 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val totalSize = (nums1.size + nums2.size)
        val (a, b) = if(nums1.size > nums2.size) nums2 to nums1 else nums1 to nums2
        // we do a binary search on the smaller array = a

        //  nums1: [1, 3, 3, 4, 5, 6, 7, 8]           size = 8
        //  nums2: [2, 3, 6, 7]                       size = 4
        //

        var left = 0
        var right = a.size
        while(left <= right) {
            val aMid = (left + right) / 2
            val bMid = ((totalSize + 1) / 2) - aMid

            val aLeft = if(aMid > 0) a[aMid - 1] else Int.MIN_VALUE
            val aRight = if(aMid < a.size) a[aMid] else Int.MAX_VALUE
            val bLeft = if(bMid > 0) b[bMid - 1] else Int.MIN_VALUE
            val bRight = if(bMid < b.size) b[bMid] else Int.MAX_VALUE

            if(aLeft <= bRight && bLeft <= aRight) {
                return if(totalSize % 2 == 0) {
                    (maxOf(aLeft,bLeft) + minOf(aRight,bRight)) / 2.0
                } else {
                    maxOf(aLeft, bLeft) / 1.0
                }
            } else if(aLeft > bRight) {
                right = aMid - 1
            } else {
                left = aMid + 1
            }
        }
        return 0.0
    }
}