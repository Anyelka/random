package com.random.problems.leetCode.subsets

class Solution2 {
    /**
     * 1. BackTracking
     *  TC: O(2 ^ n)
     *  SC: O(2 ^ n)
     * */
    fun subsets(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()

        fun collectSubsets(i: Int, subset: MutableList<Int>) {
            if(i > nums.lastIndex) {
                result.add(subset)
                return
            }
            collectSubsets(i + 1, (subset + listOf(nums[i])).toMutableList())
            collectSubsets(i + 1, subset)
        }

        collectSubsets(0, mutableListOf())

        return result.toList()
    }
}