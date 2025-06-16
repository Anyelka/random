package com.random.problems.leetCode.permutations

class Solution1 {
    fun permute(nums: IntArray): List<List<Int>> {
        return permute2(nums)
    }

    // 1. Backtracking
    //      TC: O(n * n!)
    //      SC: O(n * n!)
    private fun permute1(nums: IntArray): MutableList<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun permute(numbers: MutableSet<Int>, permutation: MutableList<Int>) {
            if (numbers.isEmpty()) result.add(permutation.toList())
            for (num in numbers) {
                val nextNumbers = numbers.toMutableSet()
                nextNumbers.remove(num)
                permutation.add(num)
                permute(nextNumbers, permutation)
                permutation.remove(num)
            }
        }

        permute(nums.toMutableSet(), mutableListOf())
        return result
    }

    // 2. Backtracking
    //      TC: O(n * n!)
    //      SC: O(n * n!)
    private fun permute2(nums: IntArray): MutableList<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun permute(permutation: MutableList<Int>) {
            if (permutation.size == nums.size) result.add(permutation.toList())
            for (num in nums) {
                if(num in permutation) continue
                permutation.add(num)
                permute(permutation)
                permutation.removeAt(permutation.lastIndex)
            }
        }

        permute(mutableListOf())
        return result
    }
}