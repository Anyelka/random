package com.random.problems.leetCode.minimumOperationsArray

class Solution1 {

    //  TC:  O(n)
    //  SC:  O(1)
    fun minOperations(nums: IntArray, k: Int): Int {
        // we are basically searching for the number of different integers above k
        return if (k > nums.min()) -1 else nums.toSet().count { it > k }
    }
}