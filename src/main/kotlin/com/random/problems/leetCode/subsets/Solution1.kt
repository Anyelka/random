package com.random.problems.leetCode.subsets

class Solution1 {
    // 1. Backtracking:
    //      TC: O(2 ^ n)
    //      SC: O(2 ^ n)
    fun subsets(nums: IntArray): List<List<Int>> {
        val subsets = mutableSetOf<List<Int>>()
        subsets.addAll(subsets(0, nums, mutableListOf()))
        return subsets.toList()
    }

    fun subsets(i: Int, nums: IntArray, subset: List<Int>): Set<List<Int>> {
        if(i == nums.size) return setOf(subset)
        return subsets(i + 1, nums, subset + nums[i]) + subsets(i + 1, nums, subset)
    }
}