package com.random.problems.leetCode.medianOfTwoSortedArrays

class Solution2 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        return findMedianSortedArrays2(nums1, nums2)
    }

    // Solution1: merge the arrays and find the median
    //      Time complexity:    O(m+n)
    //      Space complexity:   O(m+n)
    fun findMedianSortedArrays1(nums1: IntArray, nums2: IntArray): Double {
        val mergedArray = merge(nums1, nums2)
        return mergedArray.median()
    }

    fun merge(nums1: IntArray, nums2: IntArray): IntArray {
        val mergedArray = IntArray(nums1.size + nums2.size)
        var i = 0
        var j = 0
        var k = 0
        while(i < nums1.size && j < nums2.size) {
            when {
                nums1[i] < nums2[j] -> mergedArray[k++] = nums1[i++]
                else -> mergedArray[k++] = nums2[j++]
            }
        }

        while(i < nums1.size) {
            mergedArray[k++] = nums1[i++]
        }

        while(j < nums2.size) {
            mergedArray[k++] = nums2[j++]
        }

        return mergedArray
    }

    fun IntArray.median(): Double {
        return if(size % 2 == 0)
            (this[size / 2] + this[size / 2 - 1]) / 2.0
        else this[size / 2].toDouble()
    }

    //optimized solution:
    //      some sort of binary search?
    //
    //      we run a binary search in the smaller array
    //      we keep track of the left partition of the merged array by running binary search and
    //      always checking the relation between the rightmost element in the left partitions and the leftmost
    //      elements in the right partitions
    //
    //
    //      [1, 3, 7, 8, 10]        size = 5
    //      [4, 6, 8, 9, 14, 15]    size = 6
    //       0  1  2. 3. 4.  5
    //merged[1, 3, 4, 6, 7, 8, 8, 9, 10, 14, 15]       -> median = 8
    //
    //       merged array will have size = 11
    //       medianIndex:   5
    //       5 items before median
    //       5 items after median
    //
    //
    fun findMedianSortedArrays2(nums1: IntArray, nums2: IntArray): Double {
        val totalSize = nums1.size + nums2.size
        if (totalSize == 0) return 0.0

        val medianIndex = (totalSize + 1) / 2

        val (a, b) = if(nums1.size < nums2.size) nums1 to nums2 else nums2 to nums1

        var left = 0
        var right = a.size


        while(true) {
            // define the numbers on the edges:

            val aMid = (left + right) / 2
            val bMid = medianIndex - aMid

            val aLeft = if(aMid > 0) a[aMid - 1] else Int.MIN_VALUE
            val bLeft = if(bMid > 0) b[bMid - 1] else Int.MIN_VALUE
            val aRight = if(aMid < a.size) a[aMid] else Int.MAX_VALUE
            val bRight = if(bMid < b.size) b[bMid] else Int.MAX_VALUE

            // check if the numbers on the edges are in order:
            if(aLeft <= bRight && bLeft <= aRight) {
                return if(totalSize % 2 == 0)
                        (maxOf(aLeft, bLeft) + minOf(aRight, bRight)) / 2.0
                    else
                        maxOf(aLeft, bLeft).toDouble()
            }

            // update our search range:
            else if(aLeft > bRight) {
                right = aMid - 1
            } else {
                left = aMid + 1
            }

        }
    }
}