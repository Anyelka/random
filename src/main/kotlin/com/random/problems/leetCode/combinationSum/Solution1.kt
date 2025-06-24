package com.random.problems.leetCode.combinationSum

class Solution1 {
    // 1. Backtracking:
    //  TC: O(2 ^ t)
    //  SC: O(t) + O(k * klength)
    //  - where t is target
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        fun combine(index: Int, combination: MutableList<Int>, sum: Int) {
            if(sum == target) {
                result.add(combination.toList())
                return
            }
            if(index > candidates.lastIndex || sum > target) return
            combination.add(candidates[index])
            combine(index, combination, sum + candidates[index])
            combination.removeLast()
            combine(index + 1, combination, sum)
        }

        combine(0, mutableListOf(), 0)
        return result
    }
}