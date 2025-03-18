package com.random.problems.leetCode.medianOfTwoSortedArrays

class Solution1 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        return findMedianSortedArraysBinary(nums1, nums2)
    }

    // 1. Naive merge solution:
    //      merge the two arrays in order and find the median of the merged array
    //
    //      Time complexity:    O(n + m)
    //      Space complexity:   O(n + m)
    fun findMedianSortedArraysMerge(nums1: IntArray, nums2: IntArray): Double {
        val mergedArray = merge(nums1, nums2)
        return if (mergedArray.size % 2 == 0)
            (mergedArray[mergedArray.size / 2] + mergedArray[mergedArray.size / 2 - 1]).toDouble() / 2
        else mergedArray[mergedArray.size / 2].toDouble()
    }

    private fun merge(array1: IntArray, array2: IntArray): Array<Int> {
        val result = mutableListOf<Int>()
        var i = 0
        var j = 0
        while (i < array1.size && j < array2.size) {
            if (array1[i] < array2[j]) {
                result.add(array1[i])
                i++
            } else {
                result.add(array2[j])
                j++
            }
        }
        while (i < array1.size) {
            result.add(array1[i])
            i++
        }
        while (j < array2.size) {
            result.add(array2[j])
            j++
        }
        return result.toTypedArray()
    }

    // 2. "Binary search"
    //      perform a "binary search" algorithm:
    //          - find the smaller of the two arrays and use it as the base array - 'a'
    //              we will perform a "binary search"-like algorithm on the base array with two pointers and a mid index
    //              the other array will be the secondary array - 'b'
    //          - initialize the two pointers to the leftmost ('l') and rightmost ('r') elements of the base array
    //          - in theory if we merge the two arrays, the median of the merged array will be at the middle index:
    //              medianIndex = (a.size + b.size) / 2
    //          - find the middle element ('i') in the base array based on the two pointers - i = (l + r) / 2
    //              this middle element can split the base array to two arrays:
    //                  - 1 sub-array with the elements before this middle element and
    //                  - 1 sub-array with the elements after this middle element (including the middle element too)
    //                  if we take the left sub-array, all the elements in it will be smaller than the middle element
    //                  we can combine this left sub-array with a left sub-array from the secondary array, so that
    //                  the two will have a combined length that stretches until the medianIndex:
    //           - from this we can get 'j' which will be the middle element in the secondary array
    //                  j = medianIndex - i
    //          - we can find out the two-two values on the 'edges' of the left and right sub-arrays in each array
    //              we can take the ith and i-1th elements from a and the jth and j-1th elements from b
    //          - we can compare these values to see if our combined left subarrays are in proper form,
    //              so all their elements are in ascending order
    //              - the same should be true for the combined right subarrays
    //              for this we can check the two-two values on the 'edges' by comparing them with the "diagonal" values:
    //                      ... , aLeft , aRight, ...
    //                                 ^   ^
    //                                  \/
    //                                  /\
    //                                 v  v
    //                      ... , bLeft , bRight, ...
    //                  - if the right values are bigger, the combination of the two left sub-arrays are in order, so
    //                      we can return the median:
    //                      - if the size of the merged array is even, we get the average of the two middle numbers
    //                      - if the size of the merged array is odd, we get the bigger out of the two mid values
    //                  - if not, we continue our search:
    //                      - if the left value in 'a' is bigger than the right value in 'b',
    //                          than the left sub-array of 'a' is too small, so we continue the binary search
    //                          in the RIGHT sub-array of 'a' -> set the right pointer to mid - 1 in 'a'
    //                          r = i - 1
    //                      - if the left value in 'a' is smaller than the right value in 'b',
    //                          than the left sub-array of 'a' is too big, we continue the binary search
    //                          in the LEFT sub-array of 'a' -> set the left pointer to mid + 1 in 'a'
    //                          l = i + 1
    //
    //      Time complexity:    O(log(min(n,m))
    //      Space complexity:   O(1)
    fun findMedianSortedArraysBinary(array1: IntArray, array2: IntArray): Double {
        val (a, b) = if (array1.size > array2.size) array2 to array1 else array1 to array2
        var l = 0
        var r = a.size
        val mergedArraySize = a.size + b.size
        val medianIndex: Int = (mergedArraySize + 1) / 2

        while (l <= r) {
            val i = (l + r) / 2
            val j = medianIndex - i

            val aLeft = if (i > 0) a[i - 1] else Int.MIN_VALUE
            val aRight = if (i < a.size) a[i] else Int.MAX_VALUE
            val bLeft = if (j > 0) b[j - 1] else Int.MIN_VALUE
            val bRight = if (j < b.size) b[j] else Int.MAX_VALUE

            if (aLeft <= bRight && bLeft <= aRight) {
                return if (mergedArraySize % 2 == 0)
                    (maxOf(aLeft, bLeft) + minOf(aRight, bRight)).toDouble() / 2
                else
                    maxOf(aLeft, bLeft).toDouble()
            } else if (aLeft > bRight) {
                r = i - 1
            } else {
                l = i + 1
            }
        }

        throw IllegalArgumentException("Input arrays are not sorted or have invalid sizes.")
    }
}