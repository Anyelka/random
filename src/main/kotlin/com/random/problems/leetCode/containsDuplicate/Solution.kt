package com.random.problems.leetCode.containsDuplicate

class Solution {
    //      TC:     O(n)
    //      SC:     O(n)
    fun containsDuplicate(nums: IntArray): Boolean {
        val set = mutableSetOf<Int>()
        return nums.any { !set.add(it) }
    }
}